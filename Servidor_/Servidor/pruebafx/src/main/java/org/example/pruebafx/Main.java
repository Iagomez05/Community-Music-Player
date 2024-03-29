package org.example.pruebafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application {
    private static final Logger LOG = Log.getLogger(Main.class);
    private final linkedList songList = new linkedList();


    @Override
    public void start(Stage stage) throws IOException {
        INI iniReader = new INI("data.ini");
        String musicPlayer = iniReader.getValue("MusicP");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(musicPlayer));
        Scene scene = new Scene(fxmlLoader.load(), 854, 484);
        stage.setTitle("Music Player");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
