package org.example.pruebafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Main extends Application {
    private static final Logger LOG = Log.getLogger(Main.class);
    private final LinkedList songList = new LinkedList();

    @Override
    public void start(Stage stage) throws IOException {
        ApplicationSettings.getApplicationSettings();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("music_player.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 700);
        stage.setTitle("Community Music Player · Server");
        stage.setScene(scene);
        stage.setMinWidth(960);
        stage.setMinHeight(640);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

