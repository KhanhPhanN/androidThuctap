package com.example.enterc.practiceinter.Model.Helper;

import android.os.AsyncTask;
import android.util.Log;

import com.example.enterc.practiceinter.Listener.AccountCallBack;
import com.example.enterc.practiceinter.Listener.CallBack;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterHelper {

    private OkHttpClient client = new OkHttpClient();
    public static final String REQUEST_REGISTER_URL = "http://pikasmart.com/api/Users/register";
    private static final MediaType URL_ENCODED_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    private AccountCallBack accountCallBack;
    private CallBack onPreExecute;

    public RegisterHelper(CallBack onPreExecute, AccountCallBack accountCallBack) {
        this.accountCallBack = accountCallBack;
        this.onPreExecute = onPreExecute;
    }

    private class TaskSetUser extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onPreExecute.execute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String json = null;
            RequestBody body = RequestBody.create(URL_ENCODED_TYPE, strings[0]);
            Request request = new Request.Builder().url(REQUEST_REGISTER_URL).post(body).build();

            Response response;
            try{
                response = client.newCall(request).execute();
                json = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            accountCallBack.execute(s);
            Log.d("REGISTER",s);
        }
    }

    public void registerAccount(String query) {
        TaskSetUser setUser = new TaskSetUser();
        setUser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,query);
    }
}
