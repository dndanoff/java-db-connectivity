package com.github.dndanoff.db.repository;

import java.util.List;

import com.github.dndanoff.db.entity.Student;

public interface CustomStudentRepository {
    public List<Student> totallyCustomMethod(String facultyNumber);
}
