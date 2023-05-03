package com.github.dndanoff.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbRunner implements CommandLineRunner {

    private StudentRepository repository;

    public DbRunner(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        Student student = repository.findByFacultyNumber("M29641");
        System.out.println(student);
    }
}
