package com.example.hotel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



    public class DBtest {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/hotel_system?serverTimezone=UTC";
            String username = "root";
            String password = "SeifBay12#"; // change this

            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                System.out.println("✅ Connected to the database!");
                conn.close();
            } catch (SQLException e) {
                System.out.println("❌ Database connection failed:");
                e.printStackTrace();
            }
        }
    }



