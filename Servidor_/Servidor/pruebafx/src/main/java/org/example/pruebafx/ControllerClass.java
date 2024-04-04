package org.example.pruebafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private boolean mode = true;


    @FXML
    private Button Button_Last;
    @FXML
    private Button Button_NEXT;
    @FXML
    private Button Button_Pause;
    @FXML
    private Button Button_Play;
    @FXML
    private Button Button_Remove;

    @FXML
    private Label songLabel;

    @FXML
    private Slider Slider_Volume;

    @FXML
    private ProgressBar songProgressB;

    @FXML
    private ToggleButton Toggle_commode;

    @FXML
    private Slider songSlider;

    @FXML
    private Label infoLabel;


    private Media media;
    private MediaPlayer mediaPlayer;
    private final LinkedList songList = new LinkedList();
    private MetadataList miau = new MetadataList();
    private int numbersong = 0; // el  numbersong inicia en 0
    private Timer timer; //para el progreso de la canción
    private boolean running; // para el progreso de la canción
    private TimerTask task; // para el progreso de la canción


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String rutaMusica = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();
        File directory = new File(rutaMusica);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {

                songList.insert(file);
                System.out.println(file);
            }


            getSongInfo(numbersong);
            LinkedList listaAleatoria = songList.generateRandomList();

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
            LOG.error("Error loading media: " + e.getMessage());
        }
        Slider_Volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(Slider_Volume.getValue() * 0.01);
            }
        });
    }

    private void getSongInfo(Integer position) {
        SongData current = songList.find(position);
        String path = current.getTitle();
        System.out.println("Selected id ::: " + current.getId());
        System.out.println("Selected likes ::: " + current.getLikes());
        System.out.println("Selected dislikes ::: " + current.getDislikes());
        String rutaMusics = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();
        String metadata = Metadata.extractMetadata(rutaMusics + path);
        if (metadata != null) {
            infoLabel.setText(metadata);
            System.out.println(metadata);
        } else {
            System.out.println("No se pudo extraer metadatos.");
            LOG.error("NO se pudo extraer metadata");
        }
    }


    public String getSongInfo1() {
        LinkedList listaAleatoria = songList.generateRandomList();
        Node current = listaAleatoria.get();
        StringBuilder infoCompleta = new StringBuilder(); // Para almacenar toda la información de las canciones

        while (current != null) {
            String path = current.data.getName();
            String rutaMusics = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();
            String songdata = Metadata1.extractMetadata1(rutaMusics + path);

            if (songdata != null) {
                String infocancion = songdata +
                        ", " +
                        current.getLi +
                        ", " +
                        current.dislikes +
                        ", " +
                        current.id;
                infoCompleta.append(infocancion).append("\n"); // Agregar el formato de canción seguido de un salto de línea
            } else {
                System.out.println("No se pudo extraer metadatos.");
                LOG.error("NO se pudo extraer metadata");
            }
            current = current.next;
        }

        // Devolver toda la información completa de las canciones como una cadena
        System.out.println(infoCompleta);
        System.out.println("holex");
        return infoCompleta.toString();
    }
    @FXML
    public void nextSong(ActionEvent event) {
        System.out.println("next");
        songProgressB.setProgress(0);
        songSlider.setValue(0);
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
        getSongInfo(numbersong);
        //songList.removeAtIndex(numbersong);
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
        songProgressB.setProgress(0);
        songSlider.setValue(0);
        if (numbersong > 0) {
            numbersong--;
        } else {
            numbersong = songList.size() - 1;
        }
        String musicFilePath = songList.get(numbersong).toURI().toString();
        mediaPlayer.stop();
        if (running) {
            stopTimer();
        }
        media = new Media(musicFilePath);
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songList.get(numbersong).getName());
        getSongInfo(numbersong);
    }

    @FXML
    void removeSong(ActionEvent event) {
        // Remove the song from the playlist
        songList.removeAtIndex(numbersong);

        // Adjust the current song index if necessary
        if (numbersong >= songList.size()) {
            numbersong = songList.size() > 0 ? songList.size() - 1 : 0;
        }

        // Update the UI after song removal
        updateUIAfterSongRemoval();

        // Stop playback if the deleted song was currently playing
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }

        // Load the next song to play
        loadNextSong();
    }

    // Method to update the UI after removing a song
    // Método para actualizar la interfaz de usuario después de eliminar una canción
    private void updateUIAfterSongRemoval() {
        if (songList.size() > 0) {
            // Si hay canciones restantes en la lista
            if (numbersong >= songList.size()) {
                numbersong = songList.size() - 1; // Ajusta el índice si es necesario
            }
            songLabel.setText(songList.get(numbersong).getName());
        } else {
            // Si no quedan canciones en la lista, borra la etiqueta de la canción actual
            songLabel.setText("");
        }

        // Actualiza otros elementos de la interfaz de usuario según sea necesario
    }

    // Method to load the next song to play
    private void loadNextSong() {
        if (songList.size() > 0) {
            // Get the file path of the next song
            String musicFilePath = songList.get(numbersong).toURI().toString();
            // Stop any playback and load the next song
            mediaPlayer.stop();
            media = new Media(musicFilePath);
            mediaPlayer = new MediaPlayer(media);
            // Update the song label and song info
            songLabel.setText(songList.get(numbersong).getName());
            getSongInfo(numbersong);
        } else {
            // If there are no songs left, clear the media player
            mediaPlayer.stop();
            mediaPlayer = null;
            // Clear the song label
            songLabel.setText("");
        }
    }


    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {

            public void run() {
                songSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (songSlider.isValueChanging()) {
                        mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                    }
                });
                running = true;
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songSlider.setMin(0);
                songSlider.setMax(media.getDuration().toSeconds());
                songProgressB.setProgress(currentTime / end);
                songSlider.setValue(mediaPlayer.getCurrentTime().toSeconds());

                if (currentTime / end == 1) {
                    stopTimer();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stopTimer() {
        running = false;
        timer.cancel();
    }

    @FXML
    public void commumode(ActionEvent event){
        final int valor = ApplicationSettings.getApplicationSettings().getPuertoServidor();
        if (Toggle_commode.isSelected()) {
            Servidor empiezaservidor = new Servidor(this);
            empiezaservidor.iniciarServidor(valor);
        }
    }
}