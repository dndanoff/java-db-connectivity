package com.github.dndanoff.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.dndanoff.db.entity.Student;
import com.github.dndanoff.db.repository.StudentRepository;

@Component
@Order(2)
public class CustomJpaMethodsDemo implements CommandLineRunner {
    private StudentRepository repo;

    public CustomJpaMethodsDemo(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (true) {
            Student student = new Student("M-test1");
            student.setFirstName("Denis");
            student.setLastName("Danov");
            repo.save(student);

            student.setLastName("Nikolaev Danov");
            repo.save(student);

            System.out.println(repo.customFind("M-test1"));
            System.out.println(repo.customNativeFind("M-test1"));
            System.out.println(repo.totallyCustomMethod("M-test1"));
        }
    }
}
