package com.airtribe.learntrack.exception;

/**
 * Thrown when a lookup (by ID) for a Student, Course, or Enrollment
 * fails because no matching record exists.
 *
 * Checked exception on purpose: callers (service/UI layer) are expected
 * to handle "not found" as an expected, recoverable case rather than
 * letting it propagate as a crash.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
