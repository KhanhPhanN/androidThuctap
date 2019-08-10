package com.example.enterc.practiceinter.Listener;

import com.example.enterc.practiceinter.Model.VideoJSONObject;

import java.util.ArrayList;

public interface VideoCallBack {
    void execute(ArrayList<VideoJSONObject.Song>  songs);
}
