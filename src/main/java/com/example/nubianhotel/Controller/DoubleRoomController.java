package com.example.nubianhotel.Controller;

import com.example.nubianhotel.model.RoomDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoubleRoomController implements Initializable {
    @FXML
    private Button Back;
    @FXML
    private Text Status;
    @FXML
    private Text Price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String roomType = "Single";

        int available = RoomDAO.getAvailableRoomCount(roomType);
        double price = RoomDAO.getRoomPrice(roomType);

        if (available == 0) {
            Status.setText("Booked");
            Status.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else {
            Status.setText(available + "");
            Status.setText(available + " Available Rooms");
            Status.setStyle("-fx-text-fill: black; -fx-font-weight: normal;");
        }
        Price.setText(" " + price);
    }

    @FXML
    private void handleBackButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/nubianhotel/RoomTypes.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
