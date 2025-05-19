package com.example.checkout;

import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import java.io.IOException;
import java.time.LocalDate;

public class HelloController {

    public Button saveButton;

    @FXML
    private Text customername;
    @FXML
    private Text reservationno;
    @FXML
    private Text roomtype;
    @FXML
    private Text services;
    @FXML
    private Text totalpayment;

    @FXML
    private DatePicker checkoutDatePicker;

    // private static final String URL = "jdbc:mysql://localhost:3306/hotel_system";
    // private static final String USER = "root";
    // private static final String PASSWORD = "Malak*1011";

    public void initialize() {
        loadPaymentData();
    }

    private void loadPaymentData() {
        PaymentData paymentData = new PaymentDataProxy("1");

        if (paymentData != null) {
            if (customername != null) customername.setText(paymentData.getCustomerName());
            if (reservationno != null) reservationno.setText(paymentData.getReservationNo());
            if (roomtype != null) roomtype.setText(paymentData.getRoomType());
            if (services != null) services.setText(paymentData.getServices());
            if (totalpayment != null) totalpayment.setText(paymentData.getTotalPayment());
        } else {
            if (customername != null) customername.setText("No Data");
            if (reservationno != null) reservationno.setText("No Data");
            if (roomtype != null) roomtype.setText("No Data");
            if (services != null) services.setText("No Data");
            if (totalpayment != null) totalpayment.setText("No Data");
        }
    }


    @FXML
    public void GoToPayment(ActionEvent event) {
        try {
            Parent paymentRoot = FXMLLoader.load(getClass().getResource("payment.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(paymentRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveCheckoutDate(ActionEvent event) {
        LocalDate selectedDate = checkoutDatePicker.getValue();
        if (selectedDate != null) {
            /*
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT INTO checkouts (checkout_date) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDate(1, java.sql.Date.valueOf(selectedDate));
                statement.executeUpdate();
                System.out.println("Checkout date saved successfully!");
            } catch (SQLException e) {
                System.err.println("Database error!");
                e.printStackTrace();
            }
            */
            System.out.println("Checkout date saving is disabled (database code commented out).");
        } else {
            System.out.println("Please select a date!");
        }
    }


    interface PaymentData {
        String getCustomerName();
        String getReservationNo();
        String getRoomType();
        String getServices();
        String getTotalPayment();
    }

    class RealPaymentData implements PaymentData {
        private String customerName;
        private String reservationNo;
        private String roomType;
        private String services;
        private String totalPayment;

        public RealPaymentData(String reservationNo) {
            loadDataFromDatabase(reservationNo);
        }

        private void loadDataFromDatabase(String reservationNo) {
            /*
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String query = "SELECT u.username, b.booking_id, r.room_type, s.service_name, c.total_amount " +
                        "FROM bookings b " +
                        "JOIN users u ON b.user_id = u.user_id " +
                        "JOIN rooms r ON b.room_id = r.room_id " +
                        "JOIN service_requests sr ON sr.room_id = r.room_id " +
                        "JOIN service_request_details srd ON srd.request_id = sr.request_id " +
                        "JOIN services s ON srd.service_id = s.service_id " +
                        "JOIN checkouts c ON c.booking_id = b.booking_id " +
                        "WHERE b.booking_id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, reservationNo);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    customerName = rs.getString("username");
                    this.reservationNo = rs.getString("booking_id");
                    roomType = rs.getString("room_type");
                    services = rs.getString("service_name");
                    totalPayment = rs.getString("total_amount");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            */
            // Database loading is disabled (code commented out)
        }

        public String getCustomerName() { return customerName != null ? customerName : "N/A"; }
        public String getReservationNo() { return reservationNo != null ? reservationNo : "N/A"; }
        public String getRoomType() { return roomType != null ? roomType : "N/A"; }
        public String getServices() { return services != null ? services : "N/A"; }
        public String getTotalPayment() { return totalPayment != null ? totalPayment : "N/A"; }
    }


    class PaymentDataProxy implements PaymentData {
        private RealPaymentData realPaymentData;
        private String reservationNo;

        public PaymentDataProxy(String reservationNo) {
            this.reservationNo = reservationNo;
        }

        private void loadIfNecessary() {
            if (realPaymentData == null) {
                realPaymentData = new RealPaymentData(reservationNo);
            }
        }

        public String getCustomerName() {
            loadIfNecessary();
            return realPaymentData.getCustomerName();
        }

        public String getReservationNo() {
            loadIfNecessary();
            return realPaymentData.getReservationNo();
        }

        public String getRoomType() {
            loadIfNecessary();
            return realPaymentData.getRoomType();
        }

        public String getServices() {
            loadIfNecessary();
            return realPaymentData.getServices();
        }

        public String getTotalPayment() {
            loadIfNecessary();
            return realPaymentData.getTotalPayment();
        }
    }
}
