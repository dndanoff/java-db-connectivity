package com.github.dndanoff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String facultyNumber;
    private LocalDateTime created;
    private LocalDateTime deleted;

    public static Student createNew(String firstName,
            String lastName,
            String facultyNumber) {
        return new Student(-1L, firstName, lastName, facultyNumber, LocalDateTime.now(), null);
    }

    public static Student create(Long id, String firstName,
            String lastName,
            String facultyNumber,
            LocalDateTime created,
            LocalDateTime deleted) {
        if (id < 0) {
            throw new IllegalStateException();
        }
        return new Student(id, firstName, lastName, facultyNumber, created, deleted);
    }

    private Student(Long id,
            String firstName,
            String lastName,
            String facultyNumber,
            LocalDateTime created,
            LocalDateTime deleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.facultyNumber = facultyNumber;
        this.created = created;
        this.deleted = deleted;
    }

    public void delete() {
        deleted = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public boolean isActive() {
        return deleted == null;
    }

    public boolean isNew() {
        return id < 0;
    }

    public String toString() {
        return String.format("ID: %d, FIRST_NAME: %s, LAST_NAME: %s, FACULTY_NUMBER: %s, CREATED: %s, DELETED: %s", id,
                firstName,
                lastName,
                facultyNumber,
                created.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                deleted != null ? deleted.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
    }
}
