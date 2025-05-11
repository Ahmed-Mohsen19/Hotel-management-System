package com.example.myjavafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {

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
    private void handleDontHaveAccountClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent signupRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(signupRoot));
            stage.show();

            Stage currentStage = (Stage) dont_have_account_btn.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginButtonClick() {
        String username = login_username.getText().trim();
        String password = login_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Please enter both username and password.");
            return;
        }

        try {
            Connection conn = database.getInstance().getConnection(); // ✅ Updated to use Singleton

            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");

                // Replace with actual dashboard or next page
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
                Parent dashboardRoot = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(dashboardRoot));
                stage.show();

                Stage currentStage = (Stage) login_btn.getScene().getWindow();
                currentStage.close();
            } else {
                System.out.println("Invalid username or password.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
