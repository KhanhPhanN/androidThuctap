package com.example.enterc.practiceinter.Model.Helper;

import android.content.Context;
import android.os.AsyncTask;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Model.user.UserProfile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SignoutHelper {
    private OkHttpClient client = new OkHttpClient();

    private static final MediaType URL_ENCODED_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    private static final String URL_REQUEST = "http://pikasmart.com/api/Users/signout?access_token=";
    private PreferenceHelper preferenceHelper;
    private CallBack onPostExecute;

    public SignoutHelper(Context context, CallBack onPostExecute) {
        preferenceHelper = new PreferenceHelper(context, GlobalHelper.PREFERENCE_NAME_PIKA);
        this.onPostExecute = onPostExecute;
    }

    private class TaskGetUser extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            UserProfile userProfile = new Gson().fromJson(preferenceHelper.getProfile(), UserProfile.class);
            if (userProfile != null && userProfile.getUser() != null) {
                String query = URL_REQUEST + userProfile.getUser().getAccessToken();
                RequestBody body = RequestBody.create(URL_ENCODED_TYPE, query);
                Request request = new Request.Builder().url(query).post(body).build();
                try {
                    client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            preferenceHelper.setSignin(0);
            onPostExecute.execute();
        }
    }

    public void signoutAccount() {
        SignoutHelper.TaskGetUser getUser = new SignoutHelper.TaskGetUser();
        getUser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
