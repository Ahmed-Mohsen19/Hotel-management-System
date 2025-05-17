package com.example.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB instance;
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/hotel_system";
    private final String USER = "root";
    private final String PASSWORD = "0000";

    private ConnectionDB() {
        connect();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect(); // إعادة الاتصال لو كان null أو مقفول
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


