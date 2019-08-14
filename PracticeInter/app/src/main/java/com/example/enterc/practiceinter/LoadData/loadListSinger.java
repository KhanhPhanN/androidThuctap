package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.SingerCallBack;
import com.example.enterc.practiceinter.Model.Singer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class loadListSinger extends AsyncTask<String, Void, String>{
    ArrayList<Singer> listFolows;
    SingerCallBack callBack;
    public loadListSinger(ArrayList<Singer> listFolows, SingerCallBack callBack) {
        this.listFolows = listFolows;
        this.callBack   = callBack;
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String test = loadList(strings[0]);
        return test;

    }
    @Override
    protected void onPostExecute(String s) {
        if(s!=null){
            try {
                JSONObject object = new JSONObject(s);
                JSONArray jsonArray = object.getJSONArray("ListFolow");
                ArrayList<Singer> singers = new ArrayList<>();
                for(int i = 0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    singers.add(new Singer(jsonObject.getInt("id_singer"),jsonObject.getString("name"),jsonObject.getString("avatar"),jsonObject.getString("type" )));
                }
                callBack.execute(singers);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        super.onPostExecute(s);
    }
}
