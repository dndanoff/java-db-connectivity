package com.github.dndanoff.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
@Component
@Order(4)
public class PersistOrderDemo implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void run(String... args) {
        if (true) {
            StudentDemoEntity student = new StudentDemoEntity();
            student.setFirstName("Denis");
            student.setLastName("Danov");
            student.setFacultyNumber("M_TEST@@@");
            em.persist(student);

            em.remove(student);

            StudentDemoEntity newStudent = new StudentDemoEntity();
            newStudent.setFirstName("Denis");
            newStudent.setLastName("Nikolaev Danov");
            newStudent.setFacultyNumber("M_TEST@@@");
            em.persist(newStudent);

            // THIS IS THE FIX
            // student.setLastName("Nikolaev Danov");
            // em.persist(student);
        }
    }
}
