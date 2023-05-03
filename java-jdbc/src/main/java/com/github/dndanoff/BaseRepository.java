package com.github.dndanoff;

import java.sql.*;

public abstract class BaseRepository {
    private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/db?useSSL=false";
    private final String USER = "user";
    private final String PASS = "password";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}