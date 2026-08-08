package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        validateCourseInput(courseName, durationInWeeks);
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courseRepository.save(course);
        return course;
    }

    /**
     * Overloaded add - no description supplied.
     */
    public Course addCourse(String courseName, int durationInWeeks) throws InvalidInputException {
        return addCourse(courseName, "", durationInWeeks);
    }

    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course findById(int id) throws EntityNotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No course found with ID " + id));
    }

    public void setActive(int id, boolean active) throws EntityNotFoundException {
        Course course = findById(id);
        course.setActive(active);
    }

    private void validateCourseInput(String courseName, int durationInWeeks) throws InvalidInputException {
        if (InputValidator.isBlank(courseName)) {
            throw new InvalidInputException("Course name cannot be empty.");
        }
        if (!InputValidator.isPositive(durationInWeeks)) {
            throw new InvalidInputException("Duration must be a positive number of weeks.");
        }
    }
}
