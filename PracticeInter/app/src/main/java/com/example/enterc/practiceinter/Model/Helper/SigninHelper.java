package com.example.enterc.practiceinter.Model.Helper;

import android.os.AsyncTask;

import com.example.enterc.practiceinter.Listener.AccountCallBack;
import com.example.enterc.practiceinter.Listener.CallBack;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SigninHelper {
    private OkHttpClient client = new OkHttpClient();

    private static final MediaType URL_ENCODED_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    private String url;
    private AccountCallBack accountCallBack;
    private CallBack onPreExecute;

    public SigninHelper(String url, CallBack onPreExecute, AccountCallBack accountCallBack) {
        this.url = url;
        this.accountCallBack = accountCallBack;
        this.onPreExecute = onPreExecute;
    }

    private class TaskGetUser extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onPreExecute.execute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String json = null;
            RequestBody body = RequestBody.create(URL_ENCODED_TYPE, strings[0]);
            Request request  = new Request.Builder().url(url).post(body).build();

            Response response;
            try {
                response = client.newCall(request).execute();
                json = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
                accountCallBack.execute(json);
        }
    }

    public void signinAccount(String query) {
        TaskGetUser getUser = new TaskGetUser();
        getUser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,query);
    }
}
