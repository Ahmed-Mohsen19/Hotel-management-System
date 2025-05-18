package com.example.nubian_hotel.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_system"; // adjust db name
    private static final String USER = "root";
    private static final String PASSWORD = "Roya@20102004";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
