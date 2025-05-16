// ServiceRequestHandler.java
package com.example.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceRequestHandler {

    private final Connection conn;

    public ServiceRequestHandler(Connection conn) {
        this.conn = conn;
    }

    // Save a service request with multiple services for a given room_id
    public boolean saveServiceRequest(int roomId, List<Service> services) throws SQLException {
        if (services == null || services.isEmpty()) {
            throw new IllegalArgumentException("No services to save.");
        }

        try {
            conn.setAutoCommit(false);

            // Insert service_requests row
            String insertRequestSql = "INSERT INTO service_requests (room_id) VALUES (?)";
            PreparedStatement psRequest = conn.prepareStatement(insertRequestSql, PreparedStatement.RETURN_GENERATED_KEYS);
            psRequest.setInt(1, roomId);
            psRequest.executeUpdate();

            ResultSet rs = psRequest.getGeneratedKeys();
            int requestId;
            if (rs.next()) {
                requestId = rs.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            // Insert each service_request_detail
            String insertDetailSql = "INSERT INTO service_request_details (request_id, service_id, quantity) VALUES (?, ?, ?)";

            PreparedStatement psDetail = conn.prepareStatement(insertDetailSql);

            for (Service service : services) {
                // Get service_id from DB for this service name and category
                int serviceId = getServiceId(service.getName(), service.getCategory());
                if (serviceId == -1) {
                    conn.rollback();
                    throw new SQLException("Service not found in DB: " + service.getName());
                }

                psDetail.setInt(1, requestId);
                psDetail.setInt(2, serviceId);
                psDetail.setInt(3, service.getQuantity());
                psDetail.addBatch();
            }

            psDetail.executeBatch();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // Helper: get service_id by service name and category
    private int getServiceId(String serviceName, String category) throws SQLException {
        String sql = "SELECT service_id FROM services WHERE service_name = ? AND category = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, serviceName);
            ps.setString(2, category);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("service_id");
            } else {
                return -1;
            }
        }
    }
}
