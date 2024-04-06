package org.example.pruebafx;

import java.io.File;
import java.io.IOException;

public class SongData {

    private String artist;
    private String title;
    private String album;
    private String genre;
    private String id;
    private int likes = 0;
    private int dislikes = 0;

    public SongData(File file) throws IOException {
        // Extraer los datos del nombre del archivo
        String fileName = file.getName();
        String[] parts = fileName.split(" - "); // Suponiendo que el nombre del archivo sigue el formato "artista - título.mp3"
        if (parts.length >= 2) {
            this.artist = parts[0];
            this.title = parts[1].replace(".mp3", ""); // Eliminar la extensión del archivo si es necesario
        } else {
            // Manejo de caso en el que el nombre del archivo no sigue el formato esperado
            this.artist = "Artista Desconocido";
            this.title = fileName.replace(".mp3", ""); // Utilizar el nombre del archivo como título
        }
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public void addLike() {
        this.likes++;
    }

    public void addDislike() {
        this.dislikes++;
    }
}
