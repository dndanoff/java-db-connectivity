package com.github.dndanoff.db;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {
    public Student findByFacultyNumber(String facultyNumber);

    // public Student findByFacultyNumberOrFacultyNumberIsNull(String facultyNumber);
}