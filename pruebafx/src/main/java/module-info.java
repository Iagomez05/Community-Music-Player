module org.example.pruebafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens org.example.pruebafx to javafx.fxml;
    exports org.example.pruebafx;
}