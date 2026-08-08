package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import java.time.LocalDate;

/**
 * Links a Student to a Course with a status and enrollment date.
 * Stores studentId/courseId (not object references) - a deliberate
 * simplification so the repository layer stays a plain lookup-by-id
 * model instead of needing to manage shared object graphs.
 */
public class Enrollment {

    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(int id, int studentId, int courseId, LocalDate enrollmentDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = EnrollmentStatus.ACTIVE; // every new enrollment starts active
    }

    /**
     * Overloaded constructor - defaults enrollment date to today.
     */
    public Enrollment(int id, int studentId, int courseId) {
        this(id, studentId, courseId, LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{id=" + id
                + ", studentId=" + studentId
                + ", courseId=" + courseId
                + ", date=" + enrollmentDate
                + ", status=" + status + "}";
    }
}
