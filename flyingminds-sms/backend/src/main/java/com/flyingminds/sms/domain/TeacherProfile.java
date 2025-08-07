package com.flyingminds.sms.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Extended profile for teachers linked 1:1 to a user account.
 * Holds academic details (subject, qualifications), contact and documents.
 */
@Entity
@Table(name = "teacher_profiles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
    private UserAccount user;

    private String subject;
    private String qualifications;
    private String contactNumber;
    private String documentsUrl;
}