package com.example.booking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Booking {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private int ssn;
    private Date checkIn;
    private Date checkOut;
    private int numGuests;

    public Booking(String firstName, String lastName, String phone, String email, int ssn, Date checkIn, Date checkOut, int numGuests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.ssn = ssn;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numGuests = numGuests;
    }

    public boolean saveToDatabase() {
        String sql = "INSERT INTO bookings (first_name, last_name, phone, email, ssn, check_in, check_out, number_of_guests) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setInt(5, ssn);
            stmt.setDate(6, checkIn);
            stmt.setDate(7, checkOut);
            stmt.setInt(8, numGuests);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}