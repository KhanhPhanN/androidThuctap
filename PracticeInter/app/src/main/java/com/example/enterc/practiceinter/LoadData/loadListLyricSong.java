package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;
import android.util.Log;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.LyricSong;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class loadListLyricSong extends AsyncTask<String , Void, String> {
    LyricSong list;
    CallBack callBack;
    ArrayList<LyricSong.Datum> lyricSongs;
    public loadListLyricSong(  ArrayList<LyricSong.Datum> lyricSongs, CallBack callBack) {
        this.lyricSongs = lyricSongs;
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
            try {
                JSONObject object = new JSONObject(s);
                JSONArray j = object.getJSONArray("Data");
                for(int i=0;i<j.length();i++){
                    JSONObject o = j.getJSONObject(i);
                    lyricSongs.add(new LyricSong.Datum(o.getInt("id"),o.getInt("song_id"),o.getString("start_time"),
                            o.getString("end_time"),o.getInt("period_order"),o.getInt("sentence_id"),o.getString("language_code"),
                            o.getString("sentence_value"),o.getString("sentence_hira"),o.getString("sentence_ro"),o.getString("sentence_vn"), null));
                }

               // list = new Gson().fromJson(s,LyricSong.class);
                Log.d("BBB","OK");
                Log.d("BBB",lyricSongs.size()+"");
                callBack.execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }


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
