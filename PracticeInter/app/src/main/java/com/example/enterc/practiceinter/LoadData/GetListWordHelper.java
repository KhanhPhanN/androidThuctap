package com.example.enterc.practiceinter.LoadData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.ListWordCallback;
import com.example.enterc.practiceinter.Model.ListWordJSONObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.View.HeaderVocabulary;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetListWordHelper extends AsyncTask<String,Void,List<MultiItemEntity>> {
    private OkHttpClient client = new OkHttpClient();
    private CallBack onPre;
    private ListWordCallback onPost;
    private WeakReference<Context> weakContext;

    public GetListWordHelper(Context context, CallBack onPre, ListWordCallback onPost) {
        this.weakContext = new WeakReference<>(context);
        this.onPre = onPre;
        this.onPost = onPost;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected List<MultiItemEntity> doInBackground(String... strings) {
        Request request = new Request.Builder()
                .url(strings[0])
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response != null) {
                ResponseBody body = response.body();
                if (body != null) {
                    ListWordJSONObject listWordJSONObject =  new Gson().fromJson(body.string(), ListWordJSONObject.class);
                    if(listWordJSONObject != null && listWordJSONObject.getData() != null){
                        List<MultiItemEntity> nLevels = new ArrayList<>();
                        ArrayList<ListWordJSONObject.NLevel> N5 = listWordJSONObject.getData().getN5();
                        ArrayList<ListWordJSONObject.NLevel> N4 = listWordJSONObject.getData().getN4();
                        ArrayList<ListWordJSONObject.NLevel> N3 = listWordJSONObject.getData().getN3();
                        ArrayList<ListWordJSONObject.NLevel> N2 = listWordJSONObject.getData().getN2();
                        ArrayList<ListWordJSONObject.NLevel> N1 = listWordJSONObject.getData().getN1();

                        if(N5 != null && !N5.isEmpty()){
                            HeaderVocabulary header = new HeaderVocabulary(weakContext.get().getString(R.string.jlpt_n5) +" ("+N5.size()+")");
                            for (ListWordJSONObject.NLevel n: N5) {
                                header.addSubItem(n);
                            }
                            nLevels.add(header);
                        }

                        if(N4 != null && !N4.isEmpty()){
                            HeaderVocabulary header = new HeaderVocabulary(weakContext.get().getString(R.string.jlpt_n4) +" ("+N4.size()+")");
                            for (ListWordJSONObject.NLevel n: N4) {
                                header.addSubItem(n);
                            }
                            nLevels.add(header);
                        }

                        if(N3 != null && !N3.isEmpty()) {
                            HeaderVocabulary header = new HeaderVocabulary(weakContext.get().getString(R.string.jlpt_n3) +" ("+N3.size()+")");
                            for (ListWordJSONObject.NLevel n: N3) {
                                header.addSubItem(n);
                            }
                            nLevels.add(header);
                        }

                        if(N2 != null && !N2.isEmpty()){
                            HeaderVocabulary header = new HeaderVocabulary(weakContext.get().getString(R.string.jlpt_n2) +" ("+N2.size()+")");
                            for (ListWordJSONObject.NLevel n: N2) {
                                header.addSubItem(n);
                            }
                            nLevels.add(header);
                        }

                        if(N1 != null && !N1.isEmpty()){
                            HeaderVocabulary header = new HeaderVocabulary(weakContext.get().getString(R.string.jlpt_n1) +" ("+N1.size()+")");
                            for (ListWordJSONObject.NLevel n: N1) {
                                header.addSubItem(n);
                            }
                            nLevels.add(header);
                        }

                        return nLevels;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPre.execute();
    }

    @Override
    protected void onPostExecute(List<MultiItemEntity> nLevelMultiItemEntities) {
        super.onPostExecute(nLevelMultiItemEntities);
        onPost.execute(nLevelMultiItemEntities);
    }
}
