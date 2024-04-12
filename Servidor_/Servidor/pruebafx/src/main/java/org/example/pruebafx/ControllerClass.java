package org.example.pruebafx;

import javafx.application.Platform;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




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
    private LinkedList listaAleatoria = songList.generateRandomList();
    private int numbersong = 0; // el  numbersong inicia en 0
    private Timer timer; //para el progreso de la canción
    private boolean running; // para el progreso de la canción
    private TimerTask task; // para el progreso de la canción
    private static final String DEFAULT_FILE_NAME = "info.json";
    @FXML
    private ListView<String> listView;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String rutaMusica = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();
        assert rutaMusica != null;
        File directory = new File(rutaMusica);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                try {
                    SongData songData = new SongData(file);
                    songList.insert(songData);
                } catch (IOException e) {
                    // Manejo de la excepción
                    e.printStackTrace();
                    LOG.error("Error al buscar el archivo: " + e.getMessage());
                }
            }
            for (int i = 0; i < songList.size(); i++) {
                SongData song = songList.get(i);
                listView.getItems().addAll(song.getTitle());
            }
            getSongInfo(numbersong);
        }
        listaAleatoria = songList.generateRandomList();


        try {
            String musicFileName = songList.get(numbersong).getTitle(); // Obtiene el nombre del archivo de música
            String musicDirectoryPath = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical(); // Obtiene la ruta del directorio de música
            String musicFilePath = musicDirectoryPath + File.separator + musicFileName;
            media = new Media(new File(musicFilePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songList.get(numbersong).getTitle());
        } catch (Exception e) {
            System.out.println("Error cargando media: " + e.getMessage());
            LOG.error("Error cargando media: " + e.getMessage());
        }

        Slider_Volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(Slider_Volume.getValue() * 0.01);
            }
        });
    }
    @FXML
    public void commumode(ActionEvent event){
        writeSongInfoToFile("info.json");
        final int valor = ApplicationSettings.getApplicationSettings().getPuertoServidor();
        if (Toggle_commode.isSelected()) {
            Servidor empiezaservidor = new Servidor(this);
            empiezaservidor.iniciarServidor(valor);

            // Asegúrate de que songList contenga la lista original de canciones
            if (!songList.isEmpty()) {
                // Limpia la lista aleatoria y agrega las canciones de songList
                songList.clear();
                songList.addAll(listaAleatoria);
                listView.getItems().clear();
                System.out.println(songList.size());
                for (int i = 0; i < songList.size(); i++) {
                    SongData song = songList.get(i);
                    listView.getItems().addAll(song.getTitle());
                }

                // Actualiza la interfaz de usuario con la nueva lista aleatoria
                updateUIAfterListaAleatoria();
                listView.refresh();
            } else {
                // Maneja el caso en que songList esté vacía
                // Puedes mostrar un mensaje de error o realizar otras acciones según sea necesario
                System.out.println("La lista de canciones está vacía");
            }
        }
    }


    public LinkedList getListaAleatoria() {
        return listaAleatoria;
    }
    public int getNumbersong() {
        return numbersong;
    }



    private void getSongInfo(Integer position) {
        SongData current = songList.find(position);
        String path = current.getTitle();
        System.out.println("Selected id ::: " + current.getId());
        System.out.println("Selected likes ::: " + current.getLikes());
        System.out.println("Selected dislikes ::: " + current.getDislikes());
        String rutaMusics = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();

        // Extract metadata using Metadata class
        SongData metadata = Metadata.extractMetadata(rutaMusics + path);

        if (metadata != null) {
            // Update UI with metadata
            infoLabel.setText("Artist: " + metadata.getArtist() + "                      " + " Title: " + metadata.getTitle() + "\n" + " Album: " + metadata.getAlbum()+ "           " + " Genre: " + metadata.getGenre()+ "\n" + " Likes: " + current.getLikes()+ "                                   " + " Dislikes: " + current.getDislikes());

            System.out.println(metadata);
        } else {
            System.out.println("No se pudo extraer metadatos.");
            LOG.error("NO se pudo extraer metadata");
        }
    }
    private void updateUIAfterListaAleatoria() {
        Platform.runLater(() -> {

            // Actualiza el índice de la canción actual si es necesario
            if (songList.size() > 0) {
                // Si hay canciones restantes en la lista
                if (numbersong >= songList.size()) {
                    numbersong = songList.size() - 1; // Ajusta el índice si es necesario
                }
                // Actualiza el Label con el título de la canción actual
                songLabel.setText(songList.get(numbersong).getTitle());
            } else {
                // Si no quedan canciones en la lista, borra el texto del Label
                songLabel.setText("");
            }

            // Si hay canciones en la lista, carga y reproduce la siguiente canción
            if (!songList.isEmpty()) {
                loadNextSong();
            }
        });
    }


    public void writeSongInfoToFile(String filePath) {
        //listaAleatoria = songList.generateRandomList();
        JSONArray commSongs = new JSONArray();

        // Suponiendo que listaAleatoria es una lista de objetos SongData
        for (int i = 0; i < listaAleatoria.size(); i++) {
            SongData current = listaAleatoria.get(i);
            String path = current.getTitle();
            String rutaMusics = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();
            String songdata = Metadata1.extractMetadata1(rutaMusics + path);

            if (songdata != null) {
                JSONObject commSong = new JSONObject();
                commSong.put("name", songdata);
                commSong.put("likes", current.getLikes());
                commSong.put("dislikes", current.getDislikes());
                commSong.put("id", current.getId());
                commSongs.add(commSong);
            } else {
                System.out.println("No se pudo extraer metadatos.");
                LOG.error("NO se pudo extraer metadata");
            }
        }

        // Write JSON array to the specified file path
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(commSongs.toJSONString());
            System.out.println("Successfully wrote JSON to file: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing JSON to file: " + filePath);
            e.printStackTrace();
        }
    }




    public String UpdateList(){
        listaAleatoria.sortByLikesAndDislikes();
        StringBuilder infoCompleta = new StringBuilder(); // Para almacenar toda la información de las canciones

        // Suponiendo que listaAleatoria es una lista de objetos SongData
        for (int i = 0; i < listaAleatoria.size(); i++) {
            SongData current = listaAleatoria.get(i);
            String path = current.getTitle();
            String rutaMusics = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();
            String songdata = Metadata1.extractMetadata1(rutaMusics + path);

            if (songdata != null) {
                String infocancion = songdata +
                        ", " +
                        current.getLikes() + // Llama al método getLikes()
                        ", " +
                        current.getDislikes() + // Llama al método getDislikes()
                        ", " +
                        current.getId(); // Llama al método getId()
                infoCompleta.append(infocancion).append("\n"); // Agregar el formato de canción seguido de un salto de línea
            } else {
                System.out.println("No se pudo extraer metadatos.");
                LOG.error("NO se pudo extraer metadata");
            }
        }

        // Devolver toda la información completa de las canciones como una cadena
        System.out.println(infoCompleta);
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
        String musicFileName = songList.get(numbersong).getTitle(); // Obtiene el nombre del archivo de música
        String musicDirectoryPath = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical(); // Obtiene la ruta del directorio de música
        String musicFilePath = musicDirectoryPath + File.separator + musicFileName;

        mediaPlayer.stop();
        if (running) {
            stopTimer();
        }
        media = new Media(new File(musicFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songList.get(numbersong).getTitle());
        getSongInfo(numbersong);
        loadNextSong();
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
        String musicFileName = songList.get(numbersong).getTitle(); // Obtiene el nombre del archivo de música
        String musicDirectoryPath = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical(); // Obtiene la ruta del directorio de música
        String musicFilePath = musicDirectoryPath + File.separator + musicFileName;

        mediaPlayer.stop();
        if (running) {
            stopTimer();
        }
        media = new Media(new File(musicFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songList.get(numbersong).getTitle());
        getSongInfo(numbersong);
        loadNextSong();
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
            songLabel.setText(songList.get(numbersong).getTitle());
        } else {
            // Si no quedan canciones en la lista, borra la etiqueta de la canción actual
            songLabel.setText("");
        }

    }

    // Method to load the next song to play
    private void loadNextSong() {

        // Verifica si hay canciones en la lista
        if (!songList.isEmpty()) {
            // Obtiene el nombre del archivo de música de la siguiente canción
            String musicFileName = songList.get(numbersong).getTitle();
            // Obtiene la ruta del directorio de música
            String musicDirectoryPath = ApplicationSettings.getApplicationSettings().getCarpetaBibliotecaMusical();
            // Construye la ruta completa del archivo de música
            String musicFilePath = musicDirectoryPath + File.separator + musicFileName;

            // Detiene cualquier reproducción actual
            mediaPlayer.stop();
            if (running) {
                stopTimer();
            }
            // Carga y reproduce la siguiente canción
            media = new Media(new File(musicFilePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            // Actualiza la etiqueta de la canción y la información de la canción
            songLabel.setText(songList.get(numbersong).getTitle());
            getSongInfo(numbersong);
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
    void refresh(ActionEvent event) {
        songList.sortByLikesAndDislikes();
        // Asegúrate de que songList contenga la lista original de canciones

            // Actualiza la interfaz de usuario con la nueva lista aleatoria
            updateUIAfterListaAleatoria();
        }



}