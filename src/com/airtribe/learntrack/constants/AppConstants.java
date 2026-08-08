package com.airtribe.learntrack.constants;

/**
 * Application-wide constant values.
 * Centralized here so magic strings/numbers don't get scattered
 * across service and UI classes.
 */
public final class AppConstants {

    // Prevent instantiation - this is a pure constants holder.
    private AppConstants() {
    }

    public static final String APP_NAME = "LearnTrack";
    public static final String DIVIDER = "----------------------------------------";
    public static final String INVALID_OPTION_MSG = "Invalid option. Please choose a number from the menu.";
    public static final String INVALID_NUMBER_MSG = "That doesn't look like a valid number. Please try again.";
    public static final String EMPTY_LIST_MSG = "No records found.";
}
