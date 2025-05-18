package com.example.nubian_hotel.controller;

import com.example.nubian_hotel.RoomType.RoomTypeFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;

public class RoomtypesController {

    @FXML
    private void handleSingleRoom(ActionEvent event) throws IOException {

        loadRoomDetail("/com/example/nubian_hotel/SingleRoompage.fxml");
    }

    @FXML
    private void handleDoubleRoom(ActionEvent event) throws IOException {

        loadRoomDetail("/com/example/nubian_hotel/Doubleroom.fxml");
    }

    @FXML
    private void handleSuite(ActionEvent event) throws IOException {

        loadRoomDetail("/com/example/nubian_hotel/Suitepage.fxml");
    }

    @FXML
    private void handleFamilyRoom(ActionEvent event) throws IOException {

        loadRoomDetail("/com/example/nubian_hotel/Familyroom.fxml");
    }


    private void loadRoomDetail(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
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
