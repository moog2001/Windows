module com.example.windows {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.windows to javafx.fxml;
    exports com.example.windows;
}