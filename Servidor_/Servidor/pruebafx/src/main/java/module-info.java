module org.example.pruebafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jaudiotagger;
    requires java.logging;
    //requires org.apache.log4j;
    requires log4j;
    requires ini4j;
    requires json.simple;
    requires com.google.gson;

    opens org.example.pruebafx to javafx.fxml;
    exports org.example.pruebafx;
}