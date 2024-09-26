package org.example.pruebafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerClass implements Initializable {


    @FXML
    private Button Button_Last;

    @FXML
    private Button Button_NEXT;

    @FXML
    private Button Button_Pause;

    @FXML
    private Label songLabel;

    @FXML
    private Button Button_Play;

    @FXML
    private Slider Slider_Volume;

    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    //private ArrayList<File> songs;
    private linkedList songList = new linkedList();
    private int numbersong = 0; // el  numbersong inicia en 0
    private boolean running; // esto es para despues

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //linkedList songList = new linkedList();

        directory = new File("music");

        files = directory.listFiles();

        if(files != null) {

            for(File file : files) {

                songList.insert(file);
                System.out.println(file);
            }
        }

        try {
            media = new Media(songList.get(numbersong).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songList.get(numbersong).getName());

            // Volumen(arreglarlo)
            Slider_Volume.valueProperty().addListener((observable, oldValue, newValue) -> {
                mediaPlayer.setVolume(newValue.doubleValue());
            });
        } catch (Exception e) {
            System.out.println("Error loading media: " + e.getMessage());
        }
    }

    @FXML
    public void nextSong(ActionEvent event) {
        System.out.println("next");
        if(numbersong < songList.size()) {
            numbersong++;
            mediaPlayer.stop();
            media = new Media(songList.get(numbersong).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songList.get(numbersong).getName());
        } else {
            numbersong = 0;
            mediaPlayer.stop();
            media = new Media(songList.get(numbersong).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songList.get(numbersong).getName());
        }
    }

    @FXML
    void pauseSong(ActionEvent event) {
        mediaPlayer.pause();
        System.out.println("pausing");
    }

    @FXML
    void playSong(ActionEvent event) {
        mediaPlayer.play();
        System.out.println("playing");
    }

    @FXML
    void previousSong(ActionEvent event) {
        if(numbersong > 0) {
            numbersong--;
            mediaPlayer.stop();
            media = new Media(songList.get(numbersong).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songList.get(numbersong).getName());
        } else {
            numbersong = songList.size() - 1;
            mediaPlayer.stop();
            media = new Media(songList.get(numbersong).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songList.get(numbersong).getName());
        }
    }
}


