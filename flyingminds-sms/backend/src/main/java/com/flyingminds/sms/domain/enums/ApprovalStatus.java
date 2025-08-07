package com.flyingminds.sms.domain.enums;

/**
 * Approval lifecycle for accounts and high-stakes actions.
 * PENDING users have read-only access until APPROVED. REJECTED ends flow. DEACTIVATED archives access.
 */
public enum ApprovalStatus {
    PENDING,
    APPROVED,
    REJECTED,
    DEACTIVATED
}