package com.flyingminds.sms.web;

import com.flyingminds.sms.domain.TeacherProfile;
import com.flyingminds.sms.service.AdminService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Admin-facing API endpoints for onboarding and approvals.
 * - POST /teachers: create one teacher
 * - POST /teachers/import: bulk CSV import (idempotent)
 * - POST /users/{id}/approve: approve a user account
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/teachers")
    public TeacherProfile createTeacher(@RequestBody CreateTeacherRequest req) {
        return adminService.createTeacher(req.getName(), req.getEmail(), req.getSubject(), req.getTemporaryPassword());
    }

    @PostMapping(value = "/teachers/import", consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<TeacherProfile> importTeachers(@RequestBody String csv) {
        return adminService.bulkCreateTeachersFromCsv(csv);
    }

    @PostMapping("/users/{userId}/approve")
    public void approve(@PathVariable Long userId) {
        adminService.approveUser(userId);
    }

    @Data
    public static class CreateTeacherRequest {
        private String name;
        private String email;
        private String subject;
        private String temporaryPassword;
    }
}