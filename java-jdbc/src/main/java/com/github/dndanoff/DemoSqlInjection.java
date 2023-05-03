package com.github.dndanoff;

import java.sql.*;
import java.util.*;

public class DemoSqlInjection {

    public static void main(String[] args) throws SQLException {
        StudentRepository repository = new StudentRepository();
        Optional<Student> studentOpt = repository.findByFacultyNumber("TEST' OR 1=1 -- ");
        System.out.println("Student:" + studentOpt.orElse(null));
    }
}
