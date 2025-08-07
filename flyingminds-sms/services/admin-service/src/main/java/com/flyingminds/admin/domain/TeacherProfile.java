package com.flyingminds.admin.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher_profiles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail; // identity resolution across services

    private String subject;
    private String qualifications;
    private String contactNumber;
    private String documentsUrl;
}