package com.flyingminds.sms.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_classes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // e.g., 10A, 9B

    private String name; // e.g., Grade 10 - Section A

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherProfile classTeacher; // linked by admin
}