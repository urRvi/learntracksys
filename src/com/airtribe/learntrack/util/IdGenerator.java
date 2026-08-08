package com.airtribe.learntrack.util;

/**
 * Generates sequential, unique IDs for each entity type.
 *
 * Each entity gets its own static counter so IDs stay predictable
 * and independent of insertion order across entity types (a Student
 * and a Course can both be #1 without colliding).
 *
 * All fields/methods are static because ID generation is a
 * cross-cutting utility concern, not tied to any single object
 * instance - there should only ever be one counter per entity type
 * for the lifetime of the running application.
 */
public final class IdGenerator {

    private static int studentIdCounter = 1;
    private static int courseIdCounter = 1;
    private static int enrollmentIdCounter = 1;

    private IdGenerator() {
        // static utility class - no instances
    }

    public static int getNextStudentId() {
        return studentIdCounter++;
    }

    public static int getNextCourseId() {
        return courseIdCounter++;
    }

    public static int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }
}
