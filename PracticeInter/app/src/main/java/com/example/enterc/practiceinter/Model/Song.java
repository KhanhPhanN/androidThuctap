package com.example.enterc.practiceinter.Model;

public class Song {
   private String name;
    private String link_img;
    private String link_youtube;
    private String song_id;
    public Song(String name, String link_img, String link_youtube, String song_id) {
        this.name = name;
        this.link_img = link_img;
        this.link_youtube = link_youtube;
        this.song_id = song_id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getLink_youtube() {
        return link_youtube;
    }

    public void setLink_youtube(String link_youtube) {
        this.link_youtube = link_youtube;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }
}
