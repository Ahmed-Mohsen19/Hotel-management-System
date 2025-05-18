package com.example.nubian_hotel.controller;
import com.example.nubian_hotel.model.RoomDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class SingleRoomController {
    @FXML
    private Label StatusLabel;

    @FXML
    private Label PriceLabel;

    public void initialize() {
        String roomType = "Single";
        int available = RoomDAO.getAvailableRoomCount(roomType);
        double price = RoomDAO.getRoomPrice(roomType);

        StatusLabel.setText(" " + available);
        PriceLabel.setText(" " + price);
    }
}
