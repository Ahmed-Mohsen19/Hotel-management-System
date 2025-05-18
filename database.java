package com.example.myjavafxapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:mysql://127.0.0.1:3307/hotel_system";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234";

    // Singleton instance
    private static ConnectionDB instance;

    // Database connection
    private Connection connection;

    // Private constructor to prevent instantiation
    private ConnectionDB() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // Public method to provide access to the Singleton instance
    public static synchronized ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    // Method to get the actual Connection object
    public Connection getConnection() {
        return connection;
    }
}
