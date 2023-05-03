package com.github.dndanoff;

import java.sql.*;
import java.util.*;

public class DemoSimpleFind {

    public static void main(String[] args) throws SQLException {
        // try {
        //     Class.forName("com.mysql.cj.jdbc.Driver");

        // } catch (ClassNotFoundException e) {
        //     System.out.println("Error: unable to load driver class!");
        //     System.exit(1);
        // }

        // try {
        //     Driver myDriver = new com.mysql.cj.jdbc.Driver();
        //     DriverManager.registerDriver(myDriver);
        // } catch (SQLException e) {
        //     System.out.println("Error: unable to load driver class!");
        //     System.exit(1);
        // }

        StudentRepository repository = new StudentRepository();
        List<Student> students = repository.findAllOld();
        System.out.println(students);
    }
}
