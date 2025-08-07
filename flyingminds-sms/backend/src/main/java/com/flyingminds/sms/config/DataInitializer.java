package com.flyingminds.sms.config;

import com.flyingminds.sms.domain.UserAccount;
import com.flyingminds.sms.domain.enums.ApprovalStatus;
import com.flyingminds.sms.domain.enums.Role;
import com.flyingminds.sms.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserAccountRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void seed() {
        ensureUser("super@flyingminds.app", "super123!", set(Role.SUPER_ADMIN), ApprovalStatus.APPROVED, "Super Admin");
        ensureUser("admin@flyingminds.app", "admin123!", set(Role.SCHOOL_ADMIN), ApprovalStatus.APPROVED, "School Admin");
        ensureUser("dept@flyingminds.app", "dept123!", set(Role.DEPT_HEAD), ApprovalStatus.APPROVED, "Dept Head");
        ensureUser("teacher@flyingminds.app", "teacher123!", set(Role.TEACHER), ApprovalStatus.PENDING, "Sample Teacher");
        ensureUser("parent@flyingminds.app", "parent123!", set(Role.PARENT), ApprovalStatus.PENDING, "Sample Parent");
        ensureUser("student@flyingminds.app", "student123!", set(Role.STUDENT), ApprovalStatus.PENDING, "Sample Student");
    }

    private Set<Role> set(Role role) {
        Set<Role> s = new HashSet<>();
        s.add(role);
        return s;
    }

    private void ensureUser(String email, String rawPassword, Set<Role> roles, ApprovalStatus status, String fullName) {
        userRepo.findByEmail(email).ifPresentOrElse(existing -> {
            // no-op if exists
        }, () -> {
            UserAccount user = UserAccount.builder()
                    .email(email)
                    .passwordHash(encoder.encode(rawPassword))
                    .roles(roles)
                    .approvalStatus(status)
                    .active(true)
                    .fullName(fullName)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
            userRepo.save(user);
        });
    }
}