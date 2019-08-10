package com.example.enterc.practiceinter.LoadData;

import android.os.AsyncTask;
import android.util.Log;

import com.example.enterc.practiceinter.Listener.PiChartCallBack;
import com.example.enterc.practiceinter.Listener.TopViewJpopCallBack;
import com.example.enterc.practiceinter.Model.ChartJpopJSONObject;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.example.enterc.practiceinter.Model.TopViewJSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LoadListTopViewJpop extends AsyncTask<String,Void, String> {
    ArrayList<TopViewJSONObject.Song> albums;
    TopViewJpopCallBack callBack;
    public LoadListTopViewJpop(ArrayList<TopViewJSONObject.Song> albums, TopViewJpopCallBack callBack) {
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
            TopViewJSONObject object = new Gson().fromJson(s,TopViewJSONObject.class);
            if(object!=null)
                callBack.execute(object.getSong());
            else
                Log.d("NULL","null");

        }

    }
}

