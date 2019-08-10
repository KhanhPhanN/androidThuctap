package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.VideoCallBack;
import com.example.enterc.practiceinter.Model.Song;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoadListNewAnime  extends AsyncTask<String,Void, String> {
    List<VideoJSONObject.Song> news;
    VideoCallBack callBack;
    public LoadListNewAnime(List<VideoJSONObject.Song> albums, VideoCallBack callBack) {
        this.news   = albums;
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
            VideoJSONObject object = new Gson().fromJson(s, VideoJSONObject.class);
            callBack.execute(object.getSong());

        }

    }
}

