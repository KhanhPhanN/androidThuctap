package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.VideoCallBack;
import com.example.enterc.practiceinter.Model.Song;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class loadListVideo extends AsyncTask<String , Void, String>{
    ArrayList<VideoJSONObject.Song> list;
   VideoCallBack callBack;

    public loadListVideo(ArrayList<VideoJSONObject.Song> list, VideoCallBack callBack) {
        this.list = list;
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s = loadList(strings[0]);
        return s;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s!=null){
       VideoJSONObject object = new Gson().fromJson(s, VideoJSONObject.class);
                if(callBack!=null)
                    callBack.execute(object.getSong());

        }

        super.onPostExecute(s);
    }

    String loadList(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
