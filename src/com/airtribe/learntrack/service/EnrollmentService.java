package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.List;

/**
 * Depends on StudentService and CourseService (not their repositories
 * directly) so that "does this student/course exist" always goes
 * through the same validation path the rest of the app uses.
 */
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                              StudentService studentService,
                              CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enroll(int studentId, int courseId, LocalDate date)
            throws EntityNotFoundException, InvalidInputException {
        Student student = studentService.findById(studentId);
        Course course = courseService.findById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student (ID " + studentId + ").");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course (ID " + courseId + ").");
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, date);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    /**
     * Overloaded enroll - defaults enrollment date to today.
     */
    public Enrollment enroll(int studentId, int courseId)
            throws EntityNotFoundException, InvalidInputException {
        return enroll(studentId, courseId, LocalDate.now());
    }

    public List<Enrollment> listAll() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> findByStudentId(int studentId) throws EntityNotFoundException {
        studentService.findById(studentId); // throws if student doesn't exist
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment updateStatus(int enrollmentId, EnrollmentStatus status) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException("No enrollment found with ID " + enrollmentId));
        enrollment.setStatus(status);
        return enrollment;
    }
}
