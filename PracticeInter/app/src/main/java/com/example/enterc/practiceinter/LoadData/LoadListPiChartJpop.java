package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;
import android.util.Log;

import com.example.enterc.practiceinter.Listener.PiChartCallBack;
import com.example.enterc.practiceinter.Listener.SongsCallBack;
import com.example.enterc.practiceinter.Model.ChartJpopJSONObject;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.example.enterc.practiceinter.Model.NewestJpopJSONOject;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LoadListPiChartJpop extends AsyncTask<String,Void, String> {
    ArrayList<ChartJpopJSONObject.Song> albums;
    PiChartCallBack callBack;
    public LoadListPiChartJpop(ArrayList<ChartJpopJSONObject.Song> albums, PiChartCallBack callBack) {
        this.albums   = albums;
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s = StringHelper.loadList(strings[0]);
        return s;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s!=null)
        {
            ChartJpopJSONObject object = new Gson().fromJson(s,ChartJpopJSONObject.class);
            if(object!=null)
                callBack.execute(object.getSong());
            else
                Log.d("NULL","null");

        }

    }
}

