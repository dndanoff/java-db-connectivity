package com.github.dndanoff.db.entity.dummyrelations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "DUMMY_STUDENTS", indexes = {
        @Index(name = "facultyNum_uniqueIndex", columnList = "facultyNumber", unique = true)
})
public class DummyStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false)
    private String firstName;
    @Column(length = 255, nullable = false)
    private String lastName;
    @Column(length = 20, unique = true, nullable = false)
    private String facultyNumber;
    @Transient
    private String notPersistedAtribute;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created;
    private LocalDateTime deleted;
    @ManyToOne
    private DummyFaculty faculty;

    // natural key is passed here so that it has value for new entities
    public DummyStudent(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    protected DummyStudent() {
    }

    public String toString() {
        return String.format("ID: %d, FIRST_NAME: %s, LAST_NAME: %s, FACULTY_NUMBER: %s, CREATED: %s, DELETED: %s", id,
                firstName,
                lastName,
                facultyNumber,
                created.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                deleted != null ? deleted.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
    }

    // There is a natural key so we use it in equals. PAy attention the natural key
    // is required in the constructor
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DummyStudent))
            return false;
        return facultyNumber.equals(((DummyStudent) o).getFacultyNumber());
    }

    @Override
    public int hashCode() {
        return getFacultyNumber().hashCode();
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
}
