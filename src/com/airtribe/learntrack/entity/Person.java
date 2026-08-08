package com.airtribe.learntrack.entity;

/**
 * Base class capturing the fields common to any person tracked by the
 * system. Student extends this. Kept intentionally small - it exists
 * to demonstrate inheritance/encapsulation, not to model every kind
 * of person LearnTrack could ever have.
 */
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Overridden by subclasses that want a more specific display format
     * (e.g. Student appending its batch). This is the polymorphism hook.
     */
    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + getDisplayName() + "', email='" + email + "'}";
    }
}
