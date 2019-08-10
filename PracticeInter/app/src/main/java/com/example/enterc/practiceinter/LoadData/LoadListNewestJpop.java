package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;
import android.util.Log;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.SongsCallBack;
import com.example.enterc.practiceinter.Model.Album;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.example.enterc.practiceinter.Model.NewestJpopJSONOject;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoadListNewestJpop extends AsyncTask<String,Void, String> {
    ArrayList<NewestJpopJSONOject.Song> albums;
    SongsCallBack callBack;
    public LoadListNewestJpop(ArrayList<NewestJpopJSONOject.Song> albums, SongsCallBack callBack) {
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
            NewestJpopJSONOject object = new Gson().fromJson(s,NewestJpopJSONOject.class);
            if(object!=null)
            callBack.execute(object.getSong());
            else
                Log.d("NULL","null");

        }

    }
}
