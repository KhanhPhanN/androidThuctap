package com.example.enterc.practiceinter.Model.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.enterc.practiceinter.Listener.AccountCallBack;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.user.UserProfile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateProfileHelper {
    private OkHttpClient client = new OkHttpClient();
    public static final String REQUEST_UPDATE_PROFILE_URL = "http://pikasmart.com/api/Users/updateProfile";
    private static final MediaType URL_ENCODED_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    private PreferenceHelper preferenceHelper;
    private AccountCallBack onPostExecute;
    private CallBack onPreExecute;

    public UpdateProfileHelper(Context context,CallBack onPreExecute, AccountCallBack onPostExecute){
        preferenceHelper = new PreferenceHelper(context, GlobalHelper.PREFERENCE_NAME_PIKA);
        this.onPostExecute = onPostExecute;
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
            UserProfile userProfile = new Gson().fromJson(preferenceHelper.getProfile(), UserProfile.class);
            String url = REQUEST_UPDATE_PROFILE_URL + "?access_token=" + userProfile.getUser().getAccessToken();
            Request request = new Request.Builder().url(url).post(body).build();

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onPostExecute.execute(s);
            Log.d("UPDATE",s);
        }
    }

    public void updateAccount(String query) {
        UpdateProfileHelper.TaskSetUser updateUser = new UpdateProfileHelper.TaskSetUser();
        updateUser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,query);
    }
}
