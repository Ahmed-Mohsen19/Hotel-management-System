package com.example.myjavafxapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

    private static database instance;
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost/customer";
    private final String USER = "root";
    private final String PASSWORD = "";

    private database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static database getInstance() {
        if (instance == null) {
            synchronized (database.class) {
                if (instance == null) {
                    instance = new database();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}