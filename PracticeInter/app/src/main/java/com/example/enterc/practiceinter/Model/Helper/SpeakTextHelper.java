package com.example.enterc.practiceinter.Model.Helper;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SpeakTextHelper {
    public static final String REQUEST_NAME_URL = "http://dws2.voicetext.jp/tomcat/servlet/vt";
    public static final String REQUEST_FILE_URL = "http://dws2.voicetext.jp/ASLCLCLVVS/JMEJSYGDCHMSMHSRKPJL/";

    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType URL_ENCODED_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");

    private static HashMap<String, String> mapNameUrl = new HashMap<>();
    private static MediaPlayer mp = new MediaPlayer();

    private static class TaskGetFileName extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            if (mapNameUrl.containsKey(strings[0])) {
                return mapNameUrl.get(strings[0]);
            }

            String json = null;

            String query = "text=" + strings[0] + "&talkID=308&volume=100&speed=80&pitch=96&dict=3";
            RequestBody body = RequestBody.create(URL_ENCODED_TYPE, query);
            Request request = new Request.Builder()
                    .url(REQUEST_NAME_URL)
                    .header("User-Agent", GlobalHelper.USER_AGENT)
                    .post(body)
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                json = response.body().string();

                if (json != null) { // luu cached
                    json = REQUEST_FILE_URL.concat(json.substring(json.indexOf('=') + 1));
                    mapNameUrl.put(strings[0], json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String audioUrl) {
            super.onPostExecute(audioUrl);

            if (audioUrl != null) {
                playAudio(audioUrl);
            }

        }
    }

    public static void SpeakText(String text) {
        TaskGetFileName taskGetFileName = new TaskGetFileName();
        taskGetFileName.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, text);
    }

    private static void playAudio(String audioUrl) {
        try {
            if (mp.isPlaying()) mp.stop();
            mp.reset();
            mp.setDataSource(audioUrl);
            mp.prepareAsync();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {

        }
    }

}
