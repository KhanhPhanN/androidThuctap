package com.example.enterc.practiceinter.Model;

public class Album {
    private String link_img_album;
    private String name_album;
    private String count;

    public Album(String link_img_album, String name_album, String count) {
        this.link_img_album = link_img_album;
        this.name_album = name_album;
        this.count = count;
    }

    public String getLink_img_album() {
        return link_img_album;
    }

    public void setLink_img_album(String link_img_album) {
        this.link_img_album = link_img_album;
    }

    public String getName_album() {
        return name_album;
    }

    public void setName_album(String name_album) {
        this.name_album = name_album;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
