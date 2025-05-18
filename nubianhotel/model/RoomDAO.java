package com.example.nubian_hotel.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class RoomDAO {
    public static int getAvailableRoomCount(String roomType) {
        String sql = "SELECT COUNT(*) FROM rooms WHERE room_type = ? AND is_available = TRUE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getRoomPrice(String roomType) {
        String sql = "SELECT price FROM rooms WHERE room_type = ? LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getDouble("price");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}

