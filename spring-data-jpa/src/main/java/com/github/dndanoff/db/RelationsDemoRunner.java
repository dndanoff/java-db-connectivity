package com.github.dndanoff.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.dndanoff.db.entity.Faculty;
import com.github.dndanoff.db.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;

@Component
@Order(1)
public class RelationsDemoRunner implements CommandLineRunner {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public void run(String... args) {
        if (false) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();

                Faculty fmi = new Faculty();
                fmi.setName("FMI");

                Student student = new Student("M_TEST");
                student.setFirstName("Denis");
                student.setLastName("Danov");

                fmi.addStudent(student);

                em.persist(fmi);
                em.flush();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
