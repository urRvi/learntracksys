package com.airtribe.learntrack.entity;

/**
 * A Student is a Person enrolled (or eligible to enroll) in courses.
 * Adds batch and active-status on top of what Person already tracks.
 */
public class Student extends Person {

    private String batch;
    private boolean active;

    /**
     * Full constructor - email supplied.
     */
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true; // new students are active by default
    }

    /**
     * Overloaded constructor - no email at signup time.
     * Demonstrates constructor overloading as required by the brief.
     */
    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "", batch);
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Overrides Person's version to include batch - the polymorphism
     * example called for in the brief. Any code holding a Person
     * reference to this Student will automatically get this behavior.
     */
    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Batch: " + batch + ")";
    }

    @Override
    public String toString() {
        return "Student{id=" + getId()
                + ", name='" + getDisplayName() + "'"
                + ", email='" + getEmail() + "'"
                + ", active=" + active + "}";
    }
}
