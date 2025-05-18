package com.example.myjavafxapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    // JDBC URL with your custom port and database name
    private static final String URL = "jdbc:mysql://127.0.0.1:3307/hotel_system";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234";

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }
}
