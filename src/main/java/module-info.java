module com.example.nubianhotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens com.example.nubianhotel to javafx.fxml;
    opens com.example.nubianhotel.Controller to javafx.fxml;
    exports com.example.nubianhotel;
    exports com.example.nubianhotel.Controller;
}