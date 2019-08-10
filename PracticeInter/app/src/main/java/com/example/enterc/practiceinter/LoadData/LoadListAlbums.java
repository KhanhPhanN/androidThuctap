package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;

import com.example.enterc.practiceinter.Model.Album;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoadListAlbums extends AsyncTask<String,Void, String> {
    List<Album> albums;
    CallBack callBack;
    public LoadListAlbums(List<Album> albums, CallBack callBack) {
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
            try {
                JSONObject ob = new JSONObject(s);
                JSONArray listJSON = ob.getJSONArray("Data");
                for(int i=0;i<listJSON.length();i++){
                    JSONObject object = listJSON.getJSONObject(i);
                    albums.add(new Album(object.getString("image"), object.getString("name"), object.getInt("count_video")+"videos"));
                }
                callBack.execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
