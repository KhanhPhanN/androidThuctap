package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.AlbumJpop;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoadListAlbumJpop extends AsyncTask<String,Void, String> {
    List<AlbumJpop.Datum> albumJpops;
    CallBack callBack;
    public LoadListAlbumJpop(List<AlbumJpop.Datum> albumJpops, CallBack callBack) {
        this.albumJpops   = albumJpops;
        this.callBack     = callBack;
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
               AlbumJpop albumJpop = new Gson().fromJson(s,AlbumJpop.class);
               albumJpops.addAll(albumJpop.getData());
               callBack.execute();

        }

    }
}
