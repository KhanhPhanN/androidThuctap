package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;
import android.util.Log;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.SongofSingerObject;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.google.gson.Gson;

import java.util.List;

public class LoadListSongofSinger extends AsyncTask<String,Void, String> {
    SongofSingerObject albumJpops;
    List<SongofSingerObject.Song> list;
    CallBack callBack;
    public LoadListSongofSinger(SongofSingerObject albumJpops, List<SongofSingerObject.Song> lis, CallBack callBack) {
        this.albumJpops   = albumJpops;
        this.list        = lis;
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
               albumJpops = new Gson().fromJson(s,SongofSingerObject.class);
               if(albumJpops!=null){
                   list.addAll(albumJpops.getSong());
                   callBack.execute();
                   Log.d("abcd", albumJpops.getSong().size()+"");
               }

            }
        }

    }
