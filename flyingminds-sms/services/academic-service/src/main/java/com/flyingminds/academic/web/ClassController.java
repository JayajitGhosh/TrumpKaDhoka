package com.flyingminds.academic.web;

import com.flyingminds.academic.domain.SchoolClass;
import com.flyingminds.academic.repository.SchoolClassRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/academic/classes")
@RequiredArgsConstructor
public class ClassController {

    private final SchoolClassRepository repo;

    @PostMapping
    public SchoolClass create(@RequestBody CreateClassRequest req) {
        return repo.findByCode(req.getCode()).orElseGet(() -> repo.save(SchoolClass.builder()
                .code(req.getCode())
                .name(req.getName())
                .build()));
    }

    @PostMapping("/{code}/assign-teacher")
    public SchoolClass assignTeacher(@PathVariable String code, @RequestBody AssignTeacherRequest req) {
        SchoolClass sc = repo.findByCode(code).orElseThrow();
        sc.setClassTeacherEmail(req.getTeacherEmail().trim().toLowerCase());
        return repo.save(sc);
    }

    @Data
    public static class CreateClassRequest {
        private String code;
        private String name;
    }

    @Data
    public static class AssignTeacherRequest {
        private String teacherEmail;
    }
}