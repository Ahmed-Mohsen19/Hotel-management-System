package com.example.booking;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        // الحصول على instance من Singleton
        ConnectionDB connectionDB = ConnectionDB.getInstance();

        // محاولة الحصول على الاتصال
        Connection conn = connectionDB.getConnection();

        // طباعة النتيجة
        if (conn != null) {
            System.out.println("✅ Test passed: Connection is established.");
        } else {
            System.out.println("❌ Test failed: Connection is null.");
        }
    }
}
