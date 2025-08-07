package com.flyingminds.sms.service;

import com.flyingminds.sms.domain.TeacherProfile;
import com.flyingminds.sms.domain.UserAccount;
import com.flyingminds.sms.domain.enums.ApprovalStatus;
import com.flyingminds.sms.domain.enums.Role;
import com.flyingminds.sms.repository.TeacherProfileRepository;
import com.flyingminds.sms.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserAccountRepository userRepo;
    private final TeacherProfileRepository teacherRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public TeacherProfile createTeacher(String name, String email, String subject, String temporaryPassword) {
        Optional<UserAccount> existing = userRepo.findByEmail(email);
        if (existing.isPresent()) {
            // If a teacher profile already exists for this user, return it; otherwise, do not mutate roles implicitly
            return teacherRepo.findByUserId(existing.get().getId())
                    .orElseThrow(() -> new IllegalStateException("User exists with email but is not a teacher: " + email));
        }
        UserAccount user = UserAccount.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(temporaryPassword))
                .roles(new HashSet<>(Arrays.asList(Role.TEACHER)))
                .approvalStatus(ApprovalStatus.PENDING)
                .active(true)
                .fullName(name)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        user = userRepo.save(user);

        TeacherProfile teacher = TeacherProfile.builder()
                .user(user)
                .subject(subject)
                .build();
        return teacherRepo.save(teacher);
    }

    @Transactional
    public List<TeacherProfile> bulkCreateTeachersFromCsv(String csv) {
        // CSV columns: name,email,subject,temporary_password (header optional)
        List<TeacherProfile> created = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        String[] lines = csv.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String raw = lines[i].trim();
            if (raw.isEmpty()) continue;
            if (i == 0 && raw.toLowerCase().contains("name") && raw.toLowerCase().contains("email")) continue; // skip header
            String[] parts = raw.split(",");
            if (parts.length < 4) continue;
            String name = parts[0].trim();
            String email = parts[1].trim().toLowerCase();
            String subject = parts[2].trim();
            String tempPassword = parts[3].trim();
            if (seen.contains(email)) continue;
            seen.add(email);
            if (userRepo.existsByEmail(email)) {
                // skip existing
                continue;
            }
            created.add(createTeacher(name, email, subject, tempPassword));
        }
        return created;
    }

    @Transactional
    public void approveUser(Long userId) {
        UserAccount user = userRepo.findById(userId).orElseThrow();
        user.setApprovalStatus(ApprovalStatus.APPROVED);
        user.setUpdatedAt(Instant.now());
        userRepo.save(user);
    }
}