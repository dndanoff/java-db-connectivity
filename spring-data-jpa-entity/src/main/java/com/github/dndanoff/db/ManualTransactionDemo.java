package com.github.dndanoff.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;

@Component
@Order(2)
public class ManualTransactionDemo implements CommandLineRunner {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public void run(String... args) {
        if (false) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                StudentDemoEntity student = new StudentDemoEntity();
                student.setFirstName("Denis");
                student.setLastName("Danov");
                student.setFacultyNumber("M_TEST");
                em.persist(student);
                em.flush();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
