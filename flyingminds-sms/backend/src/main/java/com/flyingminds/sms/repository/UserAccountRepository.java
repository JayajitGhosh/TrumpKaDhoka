package com.flyingminds.sms.repository;

import com.flyingminds.sms.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for user accounts with lookup helpers for email-based idempotency.
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmail(String email);
}