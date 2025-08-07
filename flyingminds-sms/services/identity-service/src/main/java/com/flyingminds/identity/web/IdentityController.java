package com.flyingminds.identity.web;

import com.flyingminds.identity.domain.UserAccount;
import com.flyingminds.identity.domain.enums.ApprovalStatus;
import com.flyingminds.identity.domain.enums.Role;
import com.flyingminds.identity.repository.UserAccountRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/identity")
@RequiredArgsConstructor
public class IdentityController {

    private final UserAccountRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/users")
    public UserAccount createUser(@RequestBody CreateUserRequest req) {
        String email = req.getEmail().trim().toLowerCase();
        return userRepo.findByEmail(email).orElseGet(() -> {
            Set<Role> roles = new HashSet<>(req.getRoles());
            UserAccount user = UserAccount.builder()
                    .email(email)
                    .passwordHash(encoder.encode(req.getTemporaryPassword()))
                    .roles(roles)
                    .approvalStatus(ApprovalStatus.PENDING)
                    .active(true)
                    .fullName(req.getFullName())
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
            return userRepo.save(user);
        });
    }

    @Data
    public static class CreateUserRequest {
        @NotBlank @Email
        private String email;
        @NotBlank
        private String temporaryPassword;
        @NotBlank
        private String fullName;
        private Set<Role> roles;
    }
}