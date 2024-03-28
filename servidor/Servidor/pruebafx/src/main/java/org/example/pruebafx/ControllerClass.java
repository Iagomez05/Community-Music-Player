package org.example.pruebafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.log4j.Logger;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class ControllerClass implements Initializable {

    private static final Logger LOG = Log.getLogger(ControllerClass.class);


    @FXML private Button Button_Last;

    @FXML private Button Button_NEXT;

    @FXML private Button Button_Pause;

    @FXML private Label songLabel;

    @FXML private Button Button_Play;

    @FXML private Slider Slider_Volume;

    @FXML private ProgressBar songProgressB;

    @FXML private Button Button_CommP;





    private Media media;
    private MediaPlayer mediaPlayer;
    private final linkedList songList = new linkedList();
    private int numbersong = 0; // el  numbersong inicia en 0
    private Timer timer; //para el progreso de la canción
    private boolean running; // para el progreso de la canción
    private TimerTask task; // para el progreso de la canción


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //linkedList songList = new linkedList();

        String rutaMusica = INI.Carpeta("data.ini");
        assert rutaMusica != null;
        File directory = new File(rutaMusica);

        File[] files = directory.listFiles();

        if(files != null) {

            for(File file : files) {

                songList.insert(file);
                //System.out.println(file);
            }
            getSongInfo();
            linkedList listaAleatoria = songList.generateRandomList();

            // Imprimir los elementos de la lista aleatoria
            Node current = listaAleatoria.head;
            while (current != null) {
                System.out.println(current.data.getName());
                current = current.next;
            }
        }

        try {
            media = new Media(songList.get(numbersong).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songList.get(numbersong).getName());

        } catch (Exception e) {
            System.out.println("Error loading media: " + e.getMessage());
            LOG.error("Error:: Error loading media: " + e.getMessage());
        }
        Slider_Volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(Slider_Volume.getValue() * 0.01);
            }
        });
    }

    private void getSongInfo1(Integer position) {
        Node current = songList.find(position);

        String path = current.data.getName();
        System.out.println("Selected id ::: " + current.id);
        System.out.println("Selected likes ::: " + current.likes);
        System.out.println("Selected dislikes ::: " + current.dislikes);

        String rutaMusics = INI.Carpeta1("data.ini");
        String metadata = String.valueOf(Metadata.extractMetadata(rutaMusics + path));
        if (metadata != null) {
            System.out.println(metadata);
        } else {
            System.out.println("No se pudo extraer metadatos.");
            LOG.error("Error:: Error:: NO se pudo extraer metadata");
        }
    }

    private void getSongInfo() {
        Node current = songList.head;

        while (current != null) {
            String path = current.data.getName();
            String rutaMusics = INI.Carpeta1("data.ini");
            SongData songData1 = Metadata.extractMetadata(rutaMusics + path);
            if (songData1 != null) {
                System.out.println(songData1);
            } else {
                System.out.println("No se pudo extraer metadatos.");
                LOG.error("NO se pudo extraer metadata");
            }
            current = current.next;
        }
    }


    @FXML
    public void nextSong(ActionEvent event) {
        System.out.println("next");
        if (numbersong < songList.size() - 1) {
            numbersong++;
        } else {
            numbersong = 0;
        }
        String musicFilePath = songList.get(numbersong).toURI().toString();
        mediaPlayer.stop();
        if (running) {
            stopTimer();
        }
        media = new Media(musicFilePath);
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songList.get(numbersong).getName());
        getSongInfo1(numbersong);
    }

    @FXML
    void pauseSong(ActionEvent event) {
        stopTimer();
        mediaPlayer.pause();
        System.out.println("pausing");
    }

    @FXML
    void playSong(ActionEvent event) {
        beginTimer();
        mediaPlayer.setVolume(Slider_Volume.getValue() * 0.01);
        mediaPlayer.play();
        System.out.println("playing");
    }

    @FXML
    void previousSong(ActionEvent event) {
        if (numbersong > 0) {
            numbersong--;
        } else {
            numbersong = songList.size() - 1;
        }
        mediaPlayer.stop();
        if (running) {
            stopTimer();
        }
        media = new Media(songList.get(numbersong).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songList.get(numbersong).getName());
        getSongInfo1(numbersong);
    }

    @FXML private Slider songSlider;

    public void beginTimer(){
        timer = new Timer();
        task = new TimerTask() {

            public void run() {
                running = true;
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressB.setProgress(currentTime/end);

                if (currentTime/end == 1 ){
                    stopTimer();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);

    }
    public void stopTimer(){
        running = false;
        timer.cancel();

    }

    public void CommPlayer(ActionEvent actionEvent) {
    }
}


