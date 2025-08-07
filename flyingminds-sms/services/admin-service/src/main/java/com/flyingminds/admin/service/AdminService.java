package com.flyingminds.admin.service;

import com.flyingminds.admin.domain.TeacherProfile;
import com.flyingminds.admin.repository.TeacherProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final TeacherProfileRepository teacherRepo;
    private final IdentityClient identityClient;

    @Transactional
    public TeacherProfile createTeacher(String name, String email, String subject, String temporaryPassword) {
        String normalizedEmail = email.trim().toLowerCase();
        return teacherRepo.findByUserEmail(normalizedEmail).orElseGet(() -> {
            identityClient.ensureUser(normalizedEmail, name, temporaryPassword, Set.of("TEACHER"));
            TeacherProfile tp = TeacherProfile.builder()
                    .userEmail(normalizedEmail)
                    .subject(subject)
                    .build();
            return teacherRepo.save(tp);
        });
    }

    @Transactional
    public List<TeacherProfile> bulkCreateTeachersFromCsv(String csv) {
        List<TeacherProfile> created = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        String[] lines = csv.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String raw = lines[i].trim();
            if (raw.isEmpty()) continue;
            if (i == 0 && raw.toLowerCase().contains("name") && raw.toLowerCase().contains("email")) continue;
            String[] parts = raw.split(",");
            if (parts.length < 4) continue;
            String name = parts[0].trim();
            String email = parts[1].trim().toLowerCase();
            String subject = parts[2].trim();
            String tempPassword = parts[3].trim();
            if (seen.contains(email)) continue;
            seen.add(email);
            if (teacherRepo.existsByUserEmail(email)) continue;
            created.add(createTeacher(name, email, subject, tempPassword));
        }
        return created;
    }
}