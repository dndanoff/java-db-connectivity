package com.github.dndanoff.db.entity.dummyrelations;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DUMMY_FACULTIES")
public class DummyFaculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false, unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DummyStudent> students = new HashSet<>();

    public void addStudent(DummyStudent student) {
        students.add(student);
    }

    public void removeStudent(DummyStudent student) {
        students.remove(student);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DummyStudent> getStudents() {
        return new HashSet<>(students);
    }

    public void setStudents(Set<DummyStudent> students) {
        if (students != null) {
            this.students = students;
        }
    }
}