package com.flyingminds.sms.repository;

import com.flyingminds.sms.domain.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Optional<SchoolClass> findByCode(String code);
}