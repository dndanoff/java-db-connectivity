package com.github.dndanoff;

import java.sql.*;
import java.util.*;

public class Demo {

    public static void main(String[] args) {
        try {
            StudentRepository repository = new StudentRepository();
            Student student = Student.createNew("Denis", "Danov", "random1");
            student = repository.store(student);
            Optional<Student> studentOpt = repository.findByFacultyNumber(student.getFacultyNumber());
            System.out.println("Just created student:" + studentOpt.orElse(null));
            student.delete();
            student = repository.store(student);
            studentOpt = repository.findByFacultyNumber(student.getFacultyNumber());
            System.out.println("Just updated student:" + studentOpt.orElse(null));
        } catch (SQLException e) {
            System.out.println("Error: cannot retrieve students");
            e.printStackTrace();
        }
    }
}
