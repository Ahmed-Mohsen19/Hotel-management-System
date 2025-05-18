package com.example.nubian_hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Node;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class HomePageController {
    @FXML
    private void handleGoButton(ActionEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage.setScene(new Scene(newRoot));
        stage.show();
    }

}
