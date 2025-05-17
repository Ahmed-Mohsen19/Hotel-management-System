package com.example.nubianhotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node; // Correct Node import
import javafx.stage.Stage;
import javafx.event.ActionEvent; // Correct ActionEvent import

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button RoomTypes;

    @FXML
    private void handlerRoomTypes_Statues(ActionEvent event) throws IOException {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("RoomTypes.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
