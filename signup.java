package com.example.myjavafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signup {

    @FXML
    private Button already_have_btn;

    @FXML
    private TextField confirmpass_signup;

    @FXML
    private PasswordField pass_signup;

    @FXML
    private Button signup_btn;

    @FXML
    private TextField user_signup;
    @FXML
    private void handlealreadyHaveAccountClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent signupRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(signupRoot));
            stage.show();

            // Optional: Close the current login window
            Stage currentStage = (Stage) already_have_btn.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}