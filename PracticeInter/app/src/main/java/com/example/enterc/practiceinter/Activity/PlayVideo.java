package com.example.enterc.practiceinter.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enterc.practiceinter.Model.AlbumJpop;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.example.enterc.practiceinter.Listener.ItemChangeListener;
import com.example.enterc.practiceinter.Model.LyricSong;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapterLyric;
import com.example.enterc.practiceinter.Listener.SetupLyricListener;
import com.example.enterc.practiceinter.adapter.ViewPagerAdapterDetailVideo;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayVideo extends AppCompatActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener
,YouTubePlayer.PlayerStateChangeListener, SetupLyricListener, TabLayout.OnTabSelectedListener{
YouTubePlayerSupportFragment fragmentYoutube;
YouTubePlayer youTubePlayer;
String API_KEY="AIzaSyA5Z1muPiy_PPdpLHll1DYB0hpDDXadY84";
SetupLyricListener listener;
String id;
int seekTime=0;
ArrayList<LyricSong.Datum> x;
Timer timer;
    ItemChangeListener lyricItemListener;
    LinearLayoutManager layoutManager ;
    RecyclerView recyclerView;
    RecyclerViewAdapterLyric adapter2;
    int timeStartLyric =-1,timeEndLyric=-1,lastPositionSelected=-1,lastTimeEnd=-1,lastTimeStart=-1;
    TextView tabView_1, tabView_2, tabView_3;
    String key;
    ViewPager viewPager;
    ViewPagerAdapterDetailVideo viewPagerAdapterChildImage;
    Drawable bg1Tab, bg2Tab, bg3Tab;
    TabLayout tabLayout;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        bg1Tab = getDrawable(R.drawable.tab_1_bg);
        bg2Tab = getDrawable(R.drawable.tab_2_bg);
        bg3Tab = getDrawable(R.drawable.tab_3_bg);
        Intent intent = getIntent();
        //String url = intent.getStringExtra("URL");
        VideoJSONObject.Song videoJSONObject = new Gson().fromJson(intent.getStringExtra("INFO"), VideoJSONObject.Song.class);
        String url                      = videoJSONObject.getUrl();
        key                             = String.valueOf(videoJSONObject.getId());
        String name                     = videoJSONObject.getName();
        String nameSinger               = videoJSONObject.getSlug();
        if(url!=null){
            id = url.substring(url.lastIndexOf("=")+1);
        }else{
            id="";
        }
       // key = intent.getStringExtra("id");
       // String name = intent.getStringExtra("name");
        Toast.makeText(this, key+"--"+name,Toast.LENGTH_SHORT).show();
        x = new ArrayList<>();
        TextView tv_name = findViewById(R.id.tv_name_video);
        TextView tv_name_singer = findViewById(R.id.tv_name_singer);
        tv_name.setText(name);
        tv_name_singer.setText(nameSinger);

         viewPager = findViewById(R.id.view_detail_video);
         tabLayout = findViewById(R.id.tabs_video_detail);
         tabLayout.setupWithViewPager(viewPager);
         viewPagerAdapterChildImage = new ViewPagerAdapterDetailVideo(getSupportFragmentManager(),key,key);


         viewPager.setAdapter(viewPagerAdapterChildImage);
         viewPager.setOffscreenPageLimit(3);



        intTab();

        //setupHandlerFurigana();
    }

    void  intTab(){
        tabView_1 = (TextView) View.inflate(this, R.layout.tab_layout,null);
        tabView_2 = (TextView) View.inflate(this, R.layout.tab_layout,null);
        tabView_3 = (TextView) View.inflate(this, R.layout.tab_layout,null);
        tabView_1.setText("Lyrics");
        tabView_2.setText("Vocaluation");
        tabView_3.setText("Related");
        tabView_1.setBackground(bg1Tab);
        tabView_2.setBackground(bg2Tab);
        tabView_3.setBackground(bg3Tab);
        TabLayout.Tab tab0 = tabLayout.getTabAt(0);
        if (tab0 != null) {
            tab0.setCustomView(tabView_1);
            onTabSelected(tab0);
        }
        TabLayout.Tab tab1 = tabLayout.getTabAt(1);
        if (tab1 != null) {
            tab1.setCustomView(tabView_2);
            onTabUnselected(tab1);
        }

        TabLayout.Tab tab2 = tabLayout.getTabAt(2);
        if (tab2 != null) {
            tab2.setCustomView(tabView_3);
            onTabUnselected(tab2);
        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onSuccess() {
        setupUpdateLyricListener();
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer Player, boolean b) {
        youTubePlayer = Player;
        Log.d("AAA","Player");
        youTubePlayer.cueVideo(id, 0);
        youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean b) {

            }
        });

        youTubePlayer.setPlayerStateChangeListener(this);
        listener =  this;
        listener.onSuccess();
    }

    void init(){
        fragmentYoutube = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_youtube);
        if (fragmentYoutube == null)
            return;

        try {
            fragmentYoutube.initialize(API_KEY, this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (youTubePlayer != null)
            youTubePlayer.release();
            init();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {
        try {
            if (youTubePlayer != null) {
                seekTime = youTubePlayer.getCurrentTimeMillis();
            } else
                seekTime = 0;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }


    public synchronized void setLyricItemClickListener(ItemChangeListener listener) {
        lyricItemListener = listener;
    }
    private void setupUpdateLyricListener() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                try {
                    lyricItemListener.onCurrentTime(youTubePlayer.getCurrentTimeMillis());
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                try {
                    handler.post(Update);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
        }, 0, 100);
    }

    @Override
    public void onItemClick(LyricSong.Datum datum, int position) {
        Log.d("MightyActionX", "onItemClick\n lastPositionSelected = " + lastPositionSelected + "\n position = " + position);
        if (listener != null) listener.onItemClick(datum, position);
        if (lastPositionSelected != -1 && lastPositionSelected != position)
            adapter2.unSelected(lastPositionSelected);
        lastPositionSelected = position;
        Log.d("MightyActionX", "onItemClick\n lastPositionSelected = " + lastPositionSelected + "\n position = " + position);
        if (datum != null) {
            int timeStart = StringHelper.ConvertStringToMillis(datum.getStartTime());
            int timeEnd = StringHelper.ConvertStringToMillis(datum.getEndTime());
            lastTimeStart = timeStart;
            lastTimeEnd = timeEnd;
        }
    }



    public synchronized void removeSentenceListener() {
        lyricItemListener = null;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                tabView_1.setTextColor(getResources().getColor(R.color.colorwhite));
               // tabView_1.setBackgroundColor(getResources().getColor(R.color.colorgray));
                ((GradientDrawable) bg1Tab).setColor(getResources().getColor(R.color.colorblack));
                break;
            case 1:
                tabView_2.setTextColor(getResources().getColor(R.color.colorwhite));
                //tabView_2.setBackgroundColor(getResources().getColor(R.color.colorgray));
                ((GradientDrawable) bg2Tab).setColor(getResources().getColor(R.color.colorblack));
                break;
            case 2:
                tabView_3.setTextColor(getResources().getColor(R.color.colorwhite));
                //tabView_3.setBackgroundColor(getResources().getColor(R.color.colorgray));
                ((GradientDrawable) bg3Tab).setColor(getResources().getColor(R.color.colorblack));
                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                tabView_1.setTextColor(getResources().getColor(R.color.colorblack));
               // tabView_1.setBackgroundColor(getResources().getColor(R.color.colorwhite));
                ((GradientDrawable) bg1Tab).setColor(getResources().getColor(R.color.colorwhite));
                break;
            case 1:
                tabView_2.setTextColor(getResources().getColor(R.color.colorblack));
                ((GradientDrawable) bg2Tab).setColor(getResources().getColor(R.color.colorwhite));
                break;
            case 2:
                tabView_3.setTextColor(getResources().getColor(R.color.colorblack));
                ((GradientDrawable) bg3Tab).setColor(getResources().getColor(R.color.colorwhite));
                break;
                }

        }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
