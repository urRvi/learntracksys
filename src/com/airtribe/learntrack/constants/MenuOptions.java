package com.airtribe.learntrack.constants;

/**
 * Numeric option codes used by the console menus in Main.
 * Kept as named constants instead of raw integers scattered through
 * the UI switch statements.
 */
public final class MenuOptions {

    private MenuOptions() {
    }

    // Main menu
    public static final int MAIN_STUDENT_MANAGEMENT = 1;
    public static final int MAIN_COURSE_MANAGEMENT = 2;
    public static final int MAIN_ENROLLMENT_MANAGEMENT = 3;
    public static final int MAIN_EXIT = 0;

    // Student submenu
    public static final int STUDENT_ADD = 1;
    public static final int STUDENT_VIEW_ALL = 2;
    public static final int STUDENT_SEARCH_BY_ID = 3;
    public static final int STUDENT_DEACTIVATE = 4;
    public static final int STUDENT_BACK = 0;

    // Course submenu
    public static final int COURSE_ADD = 1;
    public static final int COURSE_VIEW_ALL = 2;
    public static final int COURSE_TOGGLE_ACTIVE = 3;
    public static final int COURSE_BACK = 0;

    // Enrollment submenu
    public static final int ENROLLMENT_ADD = 1;
    public static final int ENROLLMENT_VIEW_FOR_STUDENT = 2;
    public static final int ENROLLMENT_MARK_STATUS = 3;
    public static final int ENROLLMENT_BACK = 0;
}
