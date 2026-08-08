package com.airtribe.learntrack.exception;

/**
 * Thrown when data passed into a service method violates basic
 * validation rules (blank required fields, negative duration, etc).
 * Kept separate from EntityNotFoundException because the fix is
 * different: the caller supplied bad data, nothing was "missing".
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
