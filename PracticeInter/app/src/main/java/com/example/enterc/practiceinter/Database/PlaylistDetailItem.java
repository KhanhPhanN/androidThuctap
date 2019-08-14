package com.example.enterc.practiceinter.Database;

import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;

@Table(name = "table_playlist_detail", database = Database.class)
public class PlaylistDetailItem extends BaseModel {
    @PrimaryKey
    int id;

    @Column
    String songsJSONData;

    public PlaylistDetailItem() {
    }

    public PlaylistDetailItem( String songsJSONData) {
        this.songsJSONData = songsJSONData;
    }

    public PlaylistDetailItem(int id, String s) {
        this.id = id;
     //   ArrayList<VideoJSONObject.Song> songs = new ArrayList<>();
     //   this.songsJSONData = new Gson().toJson(songs);
        this.songsJSONData  = s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongsJSONData() {
        return songsJSONData;
    }

    public void setSongsJSONData(String songsJSONData) {
        this.songsJSONData = songsJSONData;
    }
}

