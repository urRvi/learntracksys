package com.airtribe.learntrack.util;

/**
 * Small static helpers for validating raw input before it reaches
 * the service layer. Keeping these here (rather than duplicated in
 * each service) is the "don't repeat yourself" version of clean code
 * the brief asks for.
 */
public final class InputValidator {

    private InputValidator() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (isBlank(email)) {
            return false;
        }
        // Deliberately simple check - full RFC email validation is out of
        // scope for a fundamentals project.
        return email.contains("@") && email.indexOf("@") < email.lastIndexOf(".");
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }
}
