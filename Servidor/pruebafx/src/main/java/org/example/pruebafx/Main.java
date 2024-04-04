package org.example.pruebafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application {
    private static final Logger LOG = Log.getLogger(Main.class);
    private final linkedList songList = new linkedList();
    ControllerClass controlito = new ControllerClass();

    @Override
    public void start(Stage stage) throws IOException {
        INI iniReader = new INI("data.ini");
        String musicPlayer = iniReader.getValue("MusicP");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(musicPlayer));
        Scene scene = new Scene(fxmlLoader.load(), 854, 484);
        stage.setTitle("Music Player");
        stage.setScene(scene);
        stage.show();
        controlito.getSongInfo1();

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
