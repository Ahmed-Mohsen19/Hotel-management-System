package com.example.booking;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.Date;

public class HotelBookingController {

    public Button nextButton;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField ssnField;
    @FXML private DatePicker checkInField;
    @FXML private DatePicker checkOutField;
    @FXML private Spinner<Integer> guests_Spinner;
    @FXML private Text errorLabel;

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        guests_Spinner.setValueFactory(valueFactory);
        guests_Spinner.setEditable(false);
        errorLabel.setVisible(false);
    }

    @FXML
    private void handleBooking() {
        errorLabel.setVisible(false);

        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
                || phoneField.getText().isEmpty() || emailField.getText().isEmpty()
                || ssnField.getText().isEmpty()
                || checkInField.getValue() == null || checkOutField.getValue() == null
                || guests_Spinner.getValue() == null) {

            errorLabel.setText("Please fill all fields.");
            errorLabel.setStyle("-fx-fill: red;");
            errorLabel.setVisible(true);
            return;
        }

        int ssn;
        try {
            ssn = Integer.parseInt(ssnField.getText().trim());
        } catch (NumberFormatException e) {
            errorLabel.setText("SSN must be a valid number.");
            errorLabel.setStyle("-fx-fill: red;");
            errorLabel.setVisible(true);
            return;
        }

        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        
        Date checkInDate = Date.valueOf(checkInField.getValue());
        Date checkOutDate = Date.valueOf(checkOutField.getValue());

        Booking booking = new Booking(
                firstNameField.getText().trim(),
                lastNameField.getText().trim(),
                phone,
                email,
                ssn,
                checkInDate,
                checkOutDate,
                guests_Spinner.getValue()
        );

        boolean saved = booking.saveToDatabase();

        if (saved) {
            errorLabel.setText("✅ Booking successful!");
            errorLabel.setStyle("-fx-fill: green;");
        } else {
            errorLabel.setText("❌ Failed to save booking.");
            errorLabel.setStyle("-fx-fill: red;");
        }
        errorLabel.setVisible(true);
    }
}
