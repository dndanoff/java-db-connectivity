package com.github.dndanoff.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DbRunner implements CommandLineRunner {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public DbRunner(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        final String SELECT_BY_FACULTY_NUMBER_SQL = "SELECT * FROM STUDENTS WHERE FACULTY_NUMBER = :facultyNumber";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("facultyNumber", "random1");
        Student student = jdbcTemplate.queryForObject(
                SELECT_BY_FACULTY_NUMBER_SQL, namedParameters, new StudentsRowMapper());
        System.out.println(student);
    }
}

class StudentsRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = Student.create(rs.getLong("ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("FACULTY_NUMBER"),
                rs.getTimestamp("CREATED").toLocalDateTime(),
                Optional.ofNullable(rs.getTimestamp("DELETED")).map(d -> d.toLocalDateTime())
                        .orElse(null));

        return student;
    }
}
