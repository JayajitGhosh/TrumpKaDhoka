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
        if (userRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
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
        // CSV columns: name,email,subject,temporary_password
        List<TeacherProfile> created = new ArrayList<>();
        String[] lines = csv.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 4) continue;
            created.add(createTeacher(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
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