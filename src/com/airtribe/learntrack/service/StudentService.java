package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

/**
 * Business logic for student operations. The UI layer (Main) never
 * touches StudentRepository directly - it always goes through here,
 * which is where validation and ID assignment happen.
 */
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Add a student with an email supplied at signup.
     */
    public Student addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        validateName(firstName, lastName);
        if (InputValidator.isBlank(batch)) {
            throw new InvalidInputException("Batch cannot be empty.");
        }
        if (!InputValidator.isBlank(email) && !InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Email format looks invalid: " + email);
        }

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        studentRepository.save(student);
        return student;
    }

    /**
     * Overloaded add - no email yet. Demonstrates method overloading
     * at the service layer (mirrors the constructor overloading on Student).
     */
    public Student addStudent(String firstName, String lastName, String batch)
            throws InvalidInputException {
        return addStudent(firstName, lastName, "", batch);
    }

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Student findById(int id) throws EntityNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No student found with ID " + id));
    }

    public Student updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        validateName(firstName, lastName);
        Student student = findById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        return student;
    }

    /**
     * "Removing" a student really means deactivating - the brief asks
     * for a soft delete (active=false) rather than dropping the record.
     */
    public void removeStudent(int id) throws EntityNotFoundException {
        Student student = findById(id);
        student.setActive(false);
    }

    private void validateName(String firstName, String lastName) throws InvalidInputException {
        if (InputValidator.isBlank(firstName) || InputValidator.isBlank(lastName)) {
            throw new InvalidInputException("First and last name are required.");
        }
    }
}
