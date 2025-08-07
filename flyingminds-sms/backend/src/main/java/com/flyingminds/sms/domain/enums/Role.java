package com.flyingminds.sms.domain.enums;

/**
 * System roles defining RBAC hierarchy:
 * SUPER_ADMIN → SCHOOL_ADMIN → DEPT_HEAD → TEACHER/STAFF → STUDENT/PARENT → GUEST (read-only).
 * Used for authorization and UI scoping.
 */
public enum Role {
    SUPER_ADMIN,
    SCHOOL_ADMIN,
    DEPT_HEAD,
    TEACHER,
    STAFF,
    STUDENT,
    PARENT,
    GUEST
}