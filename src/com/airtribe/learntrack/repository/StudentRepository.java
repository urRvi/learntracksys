package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory storage for Student records, backed by an ArrayList.
 *
 * ArrayList over a plain array on purpose: the number of students isn't
 * known up front and shouldn't require pre-sizing or manual resizing
 * logic every time we add one - ArrayList handles growth for us and
 * gives us find/remove helpers array doesn't.
 */
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students); // defensive copy - callers can't mutate our internal list
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }
}
