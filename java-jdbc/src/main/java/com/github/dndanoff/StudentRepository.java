package com.github.dndanoff;

import java.sql.*;
import java.util.*;

public class StudentRepository extends BaseRepository {
    public List<Student> findAllOld() {
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM STUDENTS");

            while (resultSet.next()) {
                Student student = Student.create(resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("FACULTY_NUMBER"),
                        resultSet.getTimestamp("CREATED").toLocalDateTime(),
                        Optional.ofNullable(resultSet.getTimestamp("DELETED")).map(d -> d.toLocalDateTime())
                                .orElse(null));
                students.add(student);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // @formatter:off
            try{resultSet.close();}catch(Exception e){}
            try{stmt.close();}catch(Exception e){}
            // @formatter:on
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return students;
    }

    public List<Student> findAll() throws SQLException {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM STUDENTS");) {
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = Student.create(resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("FACULTY_NUMBER"),
                        resultSet.getTimestamp("CREATED").toLocalDateTime(),
                        Optional.ofNullable(resultSet.getTimestamp("DELETED")).map(d -> d.toLocalDateTime())
                                .orElse(null));
                students.add(student);
            }

            return students;
        }
    }

    // Always good to have limits
    // Always escape user input
    // "TEST' OR 1=1 -- "
    public Optional<Student> findByFacultyNumber(String facultyNumber) throws SQLException {
        try (Connection conn = getConnection()) {
            // Statement stmt = conn.createStatement();
            // ResultSet resultSet = stmt
            // .executeQuery("SELECT * FROM STUDENTS WHERE FACULTY_NUMBER='" + facultyNumber
            // + "'");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM STUDENTS WHERE FACULTY_NUMBER=?");
            stmt.setString(1, facultyNumber);
            ResultSet resultSet = stmt.executeQuery();
            Student student = null;
            if (resultSet.first()) {
                student = Student.create(resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("FACULTY_NUMBER"),
                        resultSet.getTimestamp("CREATED").toLocalDateTime(),
                        Optional.ofNullable(resultSet.getTimestamp("DELETED")).map(d -> d.toLocalDateTime())
                                .orElse(null));
            }

            return Optional.ofNullable(student);
        }
    }

    public Student store(Student student) throws SQLException {
        if (student.isNew()) {
            Long id = create(student);
            return Student.create(id, student.getFirstName(), student.getLastName(), student.getFacultyNumber(),
                    student.getCreated(), student.getDeleted());
        }

        update(student);
        return student;
    }

    private Long create(Student student) throws SQLException {
        final String INSERT_SQL = "INSERT INTO STUDENTS(FIRST_NAME, LAST_NAME, FACULTY_NUMBER, CREATED) VALUES(?,?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(INSERT_SQL,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getFacultyNumber());
            statement.setTimestamp(4, Timestamp.valueOf(student.getCreated()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    private void update(Student student) throws SQLException {
        final String UPDATE_SQL = "UPDATE STUDENTS SET FIRST_NAME=?,  LAST_NAME=?, FACULTY_NUMBER=?, DELETED=? WHERE ID=?";
        try (Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getFacultyNumber());
            statement.setTimestamp(4, Timestamp.valueOf(student.getDeleted()));
            statement.setLong(5, student.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating student failed, no rows affected.");
            }
        }
    }

}
