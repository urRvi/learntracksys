package com.airtribe.learntrack.enums;

/**
 * Represents whether a Course is currently offered.
 * Kept separate from the boolean `active` flag on Course as a readability
 * helper for console output (e.g. "ACTIVE" / "INACTIVE" labels).
 */
public enum CourseStatus {
    ACTIVE,
    INACTIVE;

    public static CourseStatus fromBoolean(boolean active) {
        return active ? ACTIVE : INACTIVE;
    }
}
