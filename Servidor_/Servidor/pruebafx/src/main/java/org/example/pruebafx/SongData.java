package org.example.pruebafx;

public class SongData {
    private String artist;
    private String title;
    private String album;
    private String genre;
    private String id;
    private int likes = 0;
    private int dislikes = 0;

    public SongData(String artist, String title, String album, String genre, String id) {
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.genre = genre;
        this.id = id;
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
