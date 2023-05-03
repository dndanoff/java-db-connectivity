package com.github.dndanoff.db.repository;

import org.springframework.data.repository.Repository;

import com.github.dndanoff.db.entity.Student;

public interface StudentRepository extends Repository<Student, Long> {
    public void save(Student student);
}
