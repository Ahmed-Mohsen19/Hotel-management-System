package com.example.nubianhotel.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class RoomTypeController {

    @FXML
    private void handleSingleRoom(ActionEvent event) throws IOException {

        loadRoomDetail("SingleRoom.fxml");
    }

    @FXML
    private void handleDoubleRoom(ActionEvent event) throws IOException {

        loadRoomDetail("DoubleRoom.fxml");
    }

    @FXML
    private void handleSuite(ActionEvent event) throws IOException {

        loadRoomDetail("SuitePage.fxml");
    }

    @FXML
    private void handleFamilyRoom(ActionEvent event) throws IOException {

        loadRoomDetail("FamilyRoom.fxml");
    }


    private void loadRoomDetail(String loadRoomDetail) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/nubianhotel/"+loadRoomDetail));
        Parent root = loader.load();

        Label nameLabel = (Label) loader.getNamespace().get("roomNameLabel");
        Label priceLabel = (Label) loader.getNamespace().get("roomPriceLabel");
        Label statusLabel = (Label) loader.getNamespace().get("roomStatusLabel");

        Stage stage = new Stage();
        stage.setTitle("Room Details");
        stage.setScene(new Scene(root));
        stage.show();
    }
}

