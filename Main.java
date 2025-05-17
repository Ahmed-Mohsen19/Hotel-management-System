package com.example.booking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CheckIn.fxml")); // غيّر الاسم هنا لو غيّرت اسم ملف FXML
        primaryStage.setTitle("HyaHHotel - Booking Form");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false); // يمنع تكبير النافذة لو مش محتاجه
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
