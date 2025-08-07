package com.flyingminds.identity.domain;

import com.flyingminds.identity.domain.enums.ApprovalStatus;
import com.flyingminds.identity.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus approvalStatus;

    @Column(nullable = false)
    private boolean active;

    private String fullName;

    private Instant lastLoginAt;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        normalize();
        Instant now = Instant.now();
        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        normalize();
        updatedAt = Instant.now();
    }

    private void normalize() {
        if (email != null) email = email.trim().toLowerCase();
    }
}