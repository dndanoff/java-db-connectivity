package com.github.dndanoff.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.github.dndanoff.db.entity.Student;

public interface StudentRepository extends Repository<Student, Long>, CustomStudentRepository {
    public void save(Student student);

    @Query("SELECT s FROM Student s WHERE s.facultyNumber = :fNumber")
    public List<Student> customFind(@Param("fNumber") String facultyNumber);

    @Query(value = "SELECT * FROM STUDENTS WHERE facultyNumber = :fNumber", nativeQuery = true)
    public List<Student> customNativeFind(@Param("fNumber") String facultyNumber);
}
