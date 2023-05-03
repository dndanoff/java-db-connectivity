package com.github.dndanoff.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.dndanoff.db.entity.dummyrelations.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
@Component
@Order(1)
public class RelationsDemo implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void run(String... args) {
        if (false) {
            DummyFaculty fmi = new DummyFaculty();
            fmi.setName("FMI");

            DummyStudent student = new DummyStudent("M_TEST");
            student.setFirstName("Denis");
            student.setLastName("Danov");
            fmi.addStudent(student);

            em.persist(fmi);
        }
    }
}
