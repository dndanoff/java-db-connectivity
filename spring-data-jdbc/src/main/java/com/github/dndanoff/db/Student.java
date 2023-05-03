package com.github.dndanoff.db;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("STUDENTS")
public class Student {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String facultyNumber;
    private LocalDateTime created;
    private LocalDateTime deleted;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
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
