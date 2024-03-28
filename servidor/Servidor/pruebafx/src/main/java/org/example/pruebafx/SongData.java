package org.example.pruebafx;

public class SongData {
    public String artist;
    private String title;
    private String album;
    private String genre;

    public SongData(String artist, String title, String album, String genre) {
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Artist: " + artist + ", Title: " + title + ", Album: " + album + ", Genre: " + genre;
    }

}
