package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory storage for Course records, backed by an ArrayList.
 */
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public void save(Course course) {
        courses.add(course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findById(int id) {
        return courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }
}
