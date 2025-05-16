package com.example.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceController {

    @FXML
    private ComboBox<String> roomComboBox;

    // Food & Beverage
    @FXML private CheckBox sandwichCheckBox;
    @FXML private Spinner<Integer> sandwichQtyField;

    @FXML private CheckBox waterbottleCheckBox;
    @FXML private Spinner<Integer> waterbottleQtyField;

    @FXML private CheckBox coffeeCheckBox;
    @FXML private Spinner<Integer> coffeeQtyField;

    // Housekeeping
    @FXML private CheckBox cleanroomCheckBox;
    @FXML private CheckBox replacetowelsCheckBox;
    @FXML private CheckBox changebedsheetsCheckBox;
    @FXML private CheckBox refilltoiletriesCheckBox;

    // Maintenance
    @FXML private ComboBox<String> IssueComboBox;
    @FXML private TextField IssueTextField;

    // Extras
    @FXML private CheckBox extrapillowCheckBox;
    @FXML private CheckBox razorkitsCheckBox;
    @FXML private CheckBox slippersCheckBox;
    @FXML private CheckBox WakeupCheckBox;

    @FXML private Button SumbitRequestButton;

    public void initialize() {
        // Initialize Spinners
        sandwichQtyField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        waterbottleQtyField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        coffeeQtyField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));

        loadAvailableRooms();

        IssueComboBox.getItems().addAll("Air Conditioning Problem", "TV Not Working", "Light Bulb Issue");

        // Add submit handler
        SumbitRequestButton.setOnAction(e -> handleSubmitRequest());
    }

    private void loadAvailableRooms() {
        String query = "SELECT room_number FROM rooms WHERE is_available = TRUE";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            roomComboBox.getItems().clear();
            while (rs.next()) {
                roomComboBox.getItems().add(rs.getString("room_number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleSubmitRequest() {
        String selectedRoom = roomComboBox.getValue();
        if (selectedRoom == null || selectedRoom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select a room.");
            return;
        }

        List<Service> selectedServices = new ArrayList<>();

        // Food & Beverage
        if (sandwichCheckBox.isSelected() && sandwichQtyField.getValue() > 0) {
            selectedServices.add(ServiceFactory.createService("FOOD & BEVERAGE", "Sandwich", 10.00, sandwichQtyField.getValue()));
        }
        if (waterbottleCheckBox.isSelected() && waterbottleQtyField.getValue() > 0) {
            selectedServices.add(ServiceFactory.createService("FOOD & BEVERAGE", "Water Bottle", 2.00, waterbottleQtyField.getValue()));
        }
        if (coffeeCheckBox.isSelected() && coffeeQtyField.getValue() > 0) {
            selectedServices.add(ServiceFactory.createService("FOOD & BEVERAGE", "Coffee", 3.00, coffeeQtyField.getValue()));
        }

        // Housekeeping
        if (cleanroomCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("HOUSEKEEPING", "Clean Room", 0.0, 1));
        }
        if (replacetowelsCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("HOUSEKEEPING", "Replace Towels", 0.0, 1));
        }
        if (changebedsheetsCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("HOUSEKEEPING", "Change Bed Sheets", 0.0, 1));
        }
        if (refilltoiletriesCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("HOUSEKEEPING", "Refill Toiletries", 0.0, 1));
        }

        // Maintenance
        String selectedIssue = IssueComboBox.getValue();
        if (selectedIssue != null && !selectedIssue.isEmpty()) {
            selectedServices.add(ServiceFactory.createService("MAINTENANCE", selectedIssue, 0.0, 1));
        } else if (!IssueTextField.getText().trim().isEmpty()) {
            selectedServices.add(ServiceFactory.createService("MAINTENANCE", IssueTextField.getText().trim(), 0.0, 1));
        }

        // Extras
        if (extrapillowCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("EXTRAS", "Extra Pillow", 0.0, 1));
        }
        if (razorkitsCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("EXTRAS", "Razor Kits", 0.0, 1));
        }
        if (slippersCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("EXTRAS", "Slippers", 0.0, 1));
        }
        if (WakeupCheckBox.isSelected()) {
            selectedServices.add(ServiceFactory.createService("EXTRAS", "Wake-Up Call", 0.0, 1));
        }

        if (selectedServices.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select at least one service.");
            return;
        }

        // Save to DB
        try {
            saveServiceRequest(selectedRoom, selectedServices);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Service request submitted successfully!");
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to save the service request.");
        }
    }

    private void saveServiceRequest(String roomNumber, List<Service> services) throws SQLException {
        String getRoomIdQuery = "SELECT room_id FROM rooms WHERE room_number = ?";
        String insertRequestQuery = "INSERT INTO service_requests (room_id) VALUES (?)";
        String insertDetailQuery = "INSERT INTO service_request_details (request_id, service_id, quantity) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement getRoomStmt = null;
        PreparedStatement insertRequestStmt = null;
        PreparedStatement insertDetailStmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            // Get room_id
            getRoomStmt = conn.prepareStatement(getRoomIdQuery);
            getRoomStmt.setString(1, roomNumber);
            rs = getRoomStmt.executeQuery();
            int roomId = -1;
            if (rs.next()) {
                roomId = rs.getInt("room_id");
            } else {
                throw new SQLException("Room not found: " + roomNumber);
            }
            rs.close();

            // Insert into service_requests
            insertRequestStmt = conn.prepareStatement(insertRequestQuery, Statement.RETURN_GENERATED_KEYS);
            insertRequestStmt.setInt(1, roomId);
            insertRequestStmt.executeUpdate();

            ResultSet generatedKeys = insertRequestStmt.getGeneratedKeys();
            int requestId;
            if (generatedKeys.next()) {
                requestId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to get generated request ID.");
            }

            // Insert service_request_details
            insertDetailStmt = conn.prepareStatement(insertDetailQuery);
            for (Service service : services) {
                int serviceId = getServiceId(conn, service.getName());
                if (serviceId == -1) {
                    throw new SQLException("Service not found: " + service.getName());
                }

                insertDetailStmt.setInt(1, requestId);
                insertDetailStmt.setInt(2, serviceId);
                insertDetailStmt.setInt(3, service.getQuantity());
                insertDetailStmt.addBatch();
            }
            insertDetailStmt.executeBatch();

            conn.commit();

        } catch (SQLException ex) {
            if (conn != null) conn.rollback();
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (getRoomStmt != null) getRoomStmt.close();
            if (insertRequestStmt != null) insertRequestStmt.close();
            if (insertDetailStmt != null) insertDetailStmt.close();
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        }
    }

    private int getServiceId(Connection conn, String serviceName) throws SQLException {
        String query = "SELECT service_id FROM services WHERE service_name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, serviceName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("service_id");
                } else {
                    return -1;
                }
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        roomComboBox.getSelectionModel().clearSelection();

        sandwichCheckBox.setSelected(false);
        sandwichQtyField.getValueFactory().setValue(0);

        waterbottleCheckBox.setSelected(false);
        waterbottleQtyField.getValueFactory().setValue(0);

        coffeeCheckBox.setSelected(false);
        coffeeQtyField.getValueFactory().setValue(0);

        cleanroomCheckBox.setSelected(false);
        replacetowelsCheckBox.setSelected(false);
        changebedsheetsCheckBox.setSelected(false);
        refilltoiletriesCheckBox.setSelected(false);

        IssueComboBox.getSelectionModel().clearSelection();
        IssueTextField.clear();

        extrapillowCheckBox.setSelected(false);
        razorkitsCheckBox.setSelected(false);
        slippersCheckBox.setSelected(false);
        WakeupCheckBox.setSelected(false);
    }
}
