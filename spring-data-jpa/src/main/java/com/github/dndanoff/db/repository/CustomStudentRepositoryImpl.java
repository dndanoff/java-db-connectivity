package com.github.dndanoff.db.repository;

import java.util.ArrayList;
import java.util.List;

import com.github.dndanoff.db.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomStudentRepositoryImpl implements CustomStudentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Student> totallyCustomMethod(String facultyNumber) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> student = query.from(Student.class);

        Path<String> facultyNumberPath = student.get("facultyNumber");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(facultyNumberPath, facultyNumber));

        query.select(student)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return em.createQuery(query)
                .getResultList();
    }
}
