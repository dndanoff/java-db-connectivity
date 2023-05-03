package com.github.dndanoff.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
@Component
@Order(3)
public class SpringTransactionDemo implements CommandLineRunner {

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
        }
    }
}
