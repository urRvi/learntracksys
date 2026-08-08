package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

/**
 * Console entry point. Deliberately kept "dumb": it displays menus,
 * reads input, and delegates every real decision to the service
 * layer. No business logic lives here.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentRepository studentRepository = new StudentRepository();
    private static final CourseRepository courseRepository = new CourseRepository();
    private static final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    private static final StudentService studentService = new StudentService(studentRepository);
    private static final CourseService courseService = new CourseService(courseRepository);
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(enrollmentRepository, studentService, courseService);

    public static void main(String[] args) {
        System.out.println("Welcome to " + AppConstants.APP_NAME);
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case MenuOptions.MAIN_STUDENT_MANAGEMENT -> studentMenu();
                case MenuOptions.MAIN_COURSE_MANAGEMENT -> courseMenu();
                case MenuOptions.MAIN_ENROLLMENT_MANAGEMENT -> enrollmentMenu();
                case MenuOptions.MAIN_EXIT -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println(AppConstants.INVALID_OPTION_MSG);
            }
        }
        scanner.close();
    }

    // ---------- Main Menu ----------

    private static void printMainMenu() {
        System.out.println("\n" + AppConstants.DIVIDER);
        System.out.println(AppConstants.APP_NAME + " - Main Menu");
        System.out.println(AppConstants.DIVIDER);
        System.out.println(MenuOptions.MAIN_STUDENT_MANAGEMENT + ". Student Management");
        System.out.println(MenuOptions.MAIN_COURSE_MANAGEMENT + ". Course Management");
        System.out.println(MenuOptions.MAIN_ENROLLMENT_MANAGEMENT + ". Enrollment Management");
        System.out.println(MenuOptions.MAIN_EXIT + ". Exit");
    }

    // ---------- Student Menu ----------

    private static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println(MenuOptions.STUDENT_ADD + ". Add new student");
            System.out.println(MenuOptions.STUDENT_VIEW_ALL + ". View all students");
            System.out.println(MenuOptions.STUDENT_SEARCH_BY_ID + ". Search student by ID");
            System.out.println(MenuOptions.STUDENT_DEACTIVATE + ". Deactivate a student");
            System.out.println(MenuOptions.STUDENT_BACK + ". Back to main menu");

            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.STUDENT_ADD -> handleAddStudent();
                case MenuOptions.STUDENT_VIEW_ALL -> handleViewAllStudents();
                case MenuOptions.STUDENT_SEARCH_BY_ID -> handleSearchStudentById();
                case MenuOptions.STUDENT_DEACTIVATE -> handleDeactivateStudent();
                case MenuOptions.STUDENT_BACK -> back = true;
                default -> System.out.println(AppConstants.INVALID_OPTION_MSG);
            }
        }
    }

    private static void handleAddStudent() {
        String firstName = readLine("First name: ");
        String lastName = readLine("Last name: ");
        String email = readLine("Email (optional, press Enter to skip): ");
        String batch = readLine("Batch: ");

        try {
            Student student = InputValidator.isBlank(email)
                    ? studentService.addStudent(firstName, lastName, batch)
                    : studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student added successfully: " + student);
        } catch (InvalidInputException e) {
            System.out.println("Could not add student: " + e.getMessage());
        }
    }

    private static void handleViewAllStudents() {
        List<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println(AppConstants.EMPTY_LIST_MSG);
            return;
        }
        students.forEach(System.out::println);
    }

    private static void handleSearchStudentById() {
        int id = readInt("Enter student ID: ");
        try {
            Student student = studentService.findById(id);
            System.out.println(student);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleDeactivateStudent() {
        int id = readInt("Enter student ID to deactivate: ");
        try {
            studentService.removeStudent(id);
            System.out.println("Student " + id + " deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // ---------- Course Menu ----------

    private static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println(MenuOptions.COURSE_ADD + ". Add new course");
            System.out.println(MenuOptions.COURSE_VIEW_ALL + ". View all courses");
            System.out.println(MenuOptions.COURSE_TOGGLE_ACTIVE + ". Activate/Deactivate a course");
            System.out.println(MenuOptions.COURSE_BACK + ". Back to main menu");

            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.COURSE_ADD -> handleAddCourse();
                case MenuOptions.COURSE_VIEW_ALL -> handleViewAllCourses();
                case MenuOptions.COURSE_TOGGLE_ACTIVE -> handleToggleCourseActive();
                case MenuOptions.COURSE_BACK -> back = true;
                default -> System.out.println(AppConstants.INVALID_OPTION_MSG);
            }
        }
    }

    private static void handleAddCourse() {
        String name = readLine("Course name: ");
        String description = readLine("Description (optional, press Enter to skip): ");
        int duration = readInt("Duration (weeks): ");

        try {
            Course course = InputValidator.isBlank(description)
                    ? courseService.addCourse(name, duration)
                    : courseService.addCourse(name, description, duration);
            System.out.println("Course added successfully: " + course);
        } catch (InvalidInputException e) {
            System.out.println("Could not add course: " + e.getMessage());
        }
    }

    private static void handleViewAllCourses() {
        List<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println(AppConstants.EMPTY_LIST_MSG);
            return;
        }
        courses.forEach(System.out::println);
    }

    private static void handleToggleCourseActive() {
        int id = readInt("Enter course ID: ");
        String choice = readLine("Activate or deactivate? (a/d): ");
        try {
            if (choice.equalsIgnoreCase("a")) {
                courseService.setActive(id, true);
                System.out.println("Course " + id + " activated.");
            } else if (choice.equalsIgnoreCase("d")) {
                courseService.setActive(id, false);
                System.out.println("Course " + id + " deactivated.");
            } else {
                System.out.println("Please enter 'a' or 'd'.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // ---------- Enrollment Menu ----------

    private static void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println(MenuOptions.ENROLLMENT_ADD + ". Enroll a student in a course");
            System.out.println(MenuOptions.ENROLLMENT_VIEW_FOR_STUDENT + ". View enrollments for a student");
            System.out.println(MenuOptions.ENROLLMENT_MARK_STATUS + ". Mark enrollment as completed/cancelled");
            System.out.println(MenuOptions.ENROLLMENT_BACK + ". Back to main menu");

            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.ENROLLMENT_ADD -> handleEnrollStudent();
                case MenuOptions.ENROLLMENT_VIEW_FOR_STUDENT -> handleViewEnrollmentsForStudent();
                case MenuOptions.ENROLLMENT_MARK_STATUS -> handleMarkEnrollmentStatus();
                case MenuOptions.ENROLLMENT_BACK -> back = true;
                default -> System.out.println(AppConstants.INVALID_OPTION_MSG);
            }
        }
    }

    private static void handleEnrollStudent() {
        int studentId = readInt("Enter student ID: ");
        int courseId = readInt("Enter course ID: ");
        try {
            Enrollment enrollment = enrollmentService.enroll(studentId, courseId);
            System.out.println("Enrollment created: " + enrollment);
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("Could not enroll: " + e.getMessage());
        }
    }

    private static void handleViewEnrollmentsForStudent() {
        int studentId = readInt("Enter student ID: ");
        try {
            List<Enrollment> enrollments = enrollmentService.findByStudentId(studentId);
            if (enrollments.isEmpty()) {
                System.out.println(AppConstants.EMPTY_LIST_MSG);
                return;
            }
            enrollments.forEach(System.out::println);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleMarkEnrollmentStatus() {
        int enrollmentId = readInt("Enter enrollment ID: ");
        String status = readLine("Mark as (completed/cancelled): ");
        try {
            EnrollmentStatus newStatus;
            if (status.equalsIgnoreCase("completed")) {
                newStatus = EnrollmentStatus.COMPLETED;
            } else if (status.equalsIgnoreCase("cancelled")) {
                newStatus = EnrollmentStatus.CANCELLED;
            } else {
                System.out.println("Please enter 'completed' or 'cancelled'.");
                return;
            }
            Enrollment updated = enrollmentService.updateStatus(enrollmentId, newStatus);
            System.out.println("Updated: " + updated);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // ---------- Input helpers ----------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println(AppConstants.INVALID_NUMBER_MSG);
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
