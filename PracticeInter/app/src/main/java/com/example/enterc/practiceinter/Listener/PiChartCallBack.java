package com.example.enterc.practiceinter.Listener;

import com.example.enterc.practiceinter.Model.ChartJpopJSONObject;

import java.util.ArrayList;

public interface PiChartCallBack {
    void execute(ArrayList<ChartJpopJSONObject.Song> songs);
}
