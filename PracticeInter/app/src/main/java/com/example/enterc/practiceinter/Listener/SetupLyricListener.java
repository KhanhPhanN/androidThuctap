package com.example.enterc.practiceinter.Listener;


import com.example.enterc.practiceinter.Model.LyricSong;

public interface SetupLyricListener {
    void onItemClick(LyricSong.Datum datum, int position);
    void onSuccess();
}