package com.flyingminds.admin.repository;

import com.flyingminds.admin.domain.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, Long> {
    Optional<TeacherProfile> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}