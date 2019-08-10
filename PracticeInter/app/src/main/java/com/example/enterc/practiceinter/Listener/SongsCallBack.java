package com.example.enterc.practiceinter.Listener;

import com.example.enterc.practiceinter.Model.NewestJpopJSONOject;

import java.util.ArrayList;
import java.util.List;

public interface SongsCallBack {
    void execute(ArrayList<NewestJpopJSONOject.Song> songs);
}
