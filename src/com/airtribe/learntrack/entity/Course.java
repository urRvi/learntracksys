package com.airtribe.learntrack.entity;

/**
 * Represents a course that students can enroll in.
 * Standalone entity (doesn't extend Person) - a course isn't a person.
 */
public class Course {

    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course(int id, String courseName, String description, int durationInWeeks) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = true;
    }

    /**
     * Overloaded constructor for quick course creation without a
     * description up front (can be added later via setter).
     */
    public Course(int id, String courseName, int durationInWeeks) {
        this(id, courseName, "", durationInWeeks);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Course{id=" + id
                + ", name='" + courseName + "'"
                + ", duration=" + durationInWeeks + "wk"
                + ", active=" + active + "}";
    }
}
