package com.github.dndanoff.db.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "STUDENTS", indexes = {
        @Index(name = "facultyNum_uniqueIndex", columnList = "facultyNumber", unique = true)
})
public class Student {
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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "details_id")
    private Details details;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    // natural key is passed here so that it has value for new entities
    public Student(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    protected Student() {
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
        if (!(o instanceof Student))
            return false;
        return facultyNumber.equals(((Student) o).getFacultyNumber());
    }

    @Override
    public int hashCode() {
        return getFacultyNumber().hashCode();
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<Course> getCourses() {
        return new HashSet<>(courses);
    }

    public void setCourses(Set<Course> courses) {
        if (courses != null) {
            this.courses = courses;
        }
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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        if (details == null) {
            if (this.details != null) {
                this.details.setStudent(null);
            }
        } else {
            details.setStudent(this);
        }
        this.details = details;
    }
}
