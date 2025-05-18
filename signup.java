package com.example.myjavafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class signup {

    @FXML
    private Button already_have_btn;

    @FXML
    private PasswordField confirmpass_signup;

    @FXML
    private PasswordField pass_signup;

    @FXML
    private Button signup_btn;

    @FXML
    private TextField user_signup;

    // Handle "Already Have an Account?" button click
    @FXML
    private void handlealreadyHaveAccountClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent loginRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(loginRoot));
            stage.show();

            // Close current signup window
            Stage currentStage = (Stage) already_have_btn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not open login screen.");
        }
    }

    // Handle sign-up logic
    @FXML
    private void handleSignUp() {
        String username = user_signup.getText().trim();
        String password = pass_signup.getText();
        String confirmPassword = confirmpass_signup.getText();

        // Input validation
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return;
        }

        try {
            Connection conn = new ConnectionDB().connect();

            // Check if username already exists
            String checkUserQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Username already exists.");
                rs.close();
                checkStmt.close();
                conn.close();
                return;
            }

            rs.close();
            checkStmt.close();

            // Insert new user into the database
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password); // In production: hash the password!
            insertStmt.executeUpdate();
            insertStmt.close();
            conn.close();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully!");

            // Clear fields
            user_signup.clear();
            pass_signup.clear();
            confirmpass_signup.clear();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not sign up. Try again.");
        }
    }

    // Utility method for showing alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
