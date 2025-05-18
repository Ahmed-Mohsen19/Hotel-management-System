package com.example.nubian_hotel.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

interface RoomDetailPage {
     void showPage() ;
}
class SingleRoompage implements RoomDetailPage {
     public void showPage() {
          try {
               Parent root = FXMLLoader.load(getClass().getResource("SingleRoompage.fxml"));
               Stage stage = new Stage();
               stage.setTitle("Single Room Details");
               stage.setScene(new Scene(root));
               stage.show();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
class DoubleRoomPage implements RoomDetailPage {
     public void showPage() {
          try {
               Parent root = FXMLLoader.load(getClass().getResource("DoubleRoom.fxml"));
               Stage stage = new Stage();
               stage.setTitle("Double Room Details");
               stage.setScene(new Scene(root));
               stage.show();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
class FamilyRoomPage implements RoomDetailPage {
     public void showPage() {
          try {
               Parent root = FXMLLoader.load(getClass().getResource("/resources/FamilyRoom.fxml"));
               Stage stage = new Stage();
               stage.setTitle("Family Room Details");
               stage.setScene(new Scene(root));
               stage.show();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
class SuitePage implements RoomDetailPage {
     public void showPage() {
          try {
               Parent root = FXMLLoader.load(getClass().getResource("/resources/Suite.fxml"));
               Stage stage = new Stage();
               stage.setTitle("Suite Details");
               stage.setScene(new Scene(root));
               stage.show();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}

