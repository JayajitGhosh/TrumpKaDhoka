package com.flyingminds.sms.repository;

import com.flyingminds.sms.domain.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, Long> {
}