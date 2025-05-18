package com.example.myjavafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button dont_have_account_btn;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane welcomeText;

    @FXML
    private Label login_error;

    private ConnectionDB connectionDB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_error.setVisible(false);
        connectionDB = ConnectionDB.getInstance();

        // Clear error message when user types again
        login_username.textProperty().addListener((obs, oldText, newText) -> login_error.setVisible(false));
        login_password.textProperty().addListener((obs, oldText, newText) -> login_error.setVisible(false));
    }

    @FXML
    private void handleDontHaveAccountClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent signupRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(signupRoot));
            stage.show();

            // Close current window
            Stage currentStage = (Stage) dont_have_account_btn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            login_error.setText("Failed to load sign-up page.");
            login_error.setVisible(true);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginButtonClick() {
        String username = login_username.getText().trim();
        String password = login_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            login_error.setText("Please enter both username and password.");
            login_error.setVisible(true);
            return;
        }

        try (Connection conn = connectionDB.getConnection()) {
            if (conn == null) {
                login_error.setText("Error connecting to database.");
                login_error.setVisible(true);
                return;
            }

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Successful login
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml")); // <-- FIXED
                        Parent dashboardRoot = fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Dashboard");
                        stage.setScene(new Scene(dashboardRoot));
                        stage.show();

                        // Close login window
                        Stage currentStage = (Stage) login_btn.getScene().getWindow();
                        currentStage.close();
                    } else {
                        login_error.setText("Invalid username or password.");
                        login_error.setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            login_error.setText("An unexpected error occurred.");
            login_error.setVisible(true);
            e.printStackTrace();
        }
    }
}
