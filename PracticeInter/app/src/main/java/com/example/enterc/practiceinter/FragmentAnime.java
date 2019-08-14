package com.example.enterc.practiceinter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Database.PlaylistDetailItem;
import com.example.enterc.practiceinter.Database.PlaylistDetailItem_Table;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.VideoCallBack;
import com.example.enterc.practiceinter.LoadData.LoadListAlbums;
import com.example.enterc.practiceinter.LoadData.LoadListNewAnime;
import com.example.enterc.practiceinter.LoadData.loadListVideo;
import com.example.enterc.practiceinter.Model.Album;
import com.example.enterc.practiceinter.Model.AlbumJpop;
import com.example.enterc.practiceinter.Model.Helper.CheckConnect;
import com.example.enterc.practiceinter.Model.Song;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.example.enterc.practiceinter.adapter.AnimeAlbumAdapter;
import com.example.enterc.practiceinter.adapter.AnimeHotAdapter;
import com.example.enterc.practiceinter.adapter.AnimeNewAdapter;
import com.example.enterc.practiceinter.adapter.ViewPagerAdapterChildImage;
import com.gauravbhola.ripplepulsebackground.RipplePulseLayout;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentAnime extends Fragment {
    @BindView(R.id.tv_name_ja)
    TextView tv_name_ja;
    @BindView(R.id.tv_name_sub)
    TextView tv_name_sub;
    @BindView(R.id.layout_anime_hot)
    LinearLayout layout;
    @BindView(R.id.place_holder_anime_hot)
    LinearLayout layoutLoading;
    @BindView(R.id.iv_video_new)
    ImageView iv_video_new;
    @BindView(R.id.tv_name_ja_new)
    TextView tv_name_ja_new;
    @BindView(R.id.tv_name_sub_new)
    TextView tv_name_sub_new;
    List<Album> albums        = new ArrayList<>();
    ArrayList<VideoJSONObject.Song> animes    = new ArrayList<>();
    ArrayList<VideoJSONObject.Song> newanimes = new ArrayList<>();
    ArrayList<VideoJSONObject.Song> listPagers = new ArrayList<>();
    CheckConnect checkConnect;
    public FragmentAnime() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_fragment_anime,container,false);
        ButterKnife.bind(this, view);
        checkConnect = new CheckConnect(getContext());
        RipplePulseLayout ripplePulseLayout = view.findViewById(R.id.layout_ripplepulse);
        ripplePulseLayout.startRippleAnimation();
        setUpViewPagerAnime(view);
        setUpAlbumAnime(view);
        setUpHotAnime(view);
        setUpNewAnime(view);

        return view;
    }

    private void setUpNewAnime(View view) {
        final LinearLayout l =   view.findViewById(R.id.place_holder_anime_new);
        final RecyclerView recyclerNewAnime = view.findViewById(R.id.rv_anime_new);
        LinearLayoutManager linearLayout1    = new LinearLayoutManager(getContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        final AnimeNewAdapter animeNewAdapter = new AnimeNewAdapter(newanimes, getContext());
        recyclerNewAnime.setLayoutManager(linearLayout1);
        recyclerNewAnime.setAdapter(animeNewAdapter);

        Glide.with(getContext()).load("https://i.ytimg.com/vi/mzJqxT1UGho/hqdefault.jpg").error(R.drawable.img_loading).into(iv_video_new);
        tv_name_ja_new.setText("KISS OF DEATH");
        tv_name_sub_new.setText("Darling in the franxx opening full");
if(checkConnect.checkInternetConnection()) {

    new LoadListNewAnime(newanimes, new VideoCallBack() {
        @Override
        public void execute(ArrayList<VideoJSONObject.Song> songs) {
            newanimes.addAll(songs);
            animeNewAdapter.notifyDataSetChanged();
            recyclerNewAnime.setVisibility(View.VISIBLE);
            l.setVisibility(View.GONE);
            FlowManager.init(new FlowConfig.Builder(getContext()).build());
            PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(100, new Gson().toJson(newanimes));
            playlistDetailItem.save();
        }
    }).execute("http://pikasmart.com/api/Songs/listnew?limit=5&skip=1&video_type=anime");
}else {
    FlowManager.init(new FlowConfig.Builder(getContext()).build());
    PlaylistDetailItem departments = new Select().from(PlaylistDetailItem.class).where(PlaylistDetailItem_Table.id.eq(100)).querySingle();
    try {
        JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
        for(int i = 0 ; i<array.length();i++)
            newanimes.add(new Gson().fromJson(array.get(i).toString(), VideoJSONObject.Song.class));
            animeNewAdapter.notifyDataSetChanged();
        recyclerNewAnime.setVisibility(View.VISIBLE);
        l.setVisibility(View.GONE);
    } catch (JSONException e) {
        e.printStackTrace();
    }
}
    }

    private void setUpHotAnime(View view) {
        final DiscreteScrollView discreteScrollView = view.findViewById(R.id.rv_anime_hot);
        AnimeHotAdapter hotAdapter = new AnimeHotAdapter(animes,getContext());
        final InfiniteScrollAdapter infiniteScrollAdapter = InfiniteScrollAdapter.wrap(hotAdapter);
        discreteScrollView.setAdapter(infiniteScrollAdapter);
if(checkConnect.checkInternetConnection()) {
    new loadListVideo(animes, new VideoCallBack() {
        @Override
        public void execute(ArrayList<VideoJSONObject.Song> songs) {
            animes.addAll(songs);
            infiniteScrollAdapter.notifyDataSetChanged();
            onItemChanged(animes.get(0));
            layout.setVisibility(View.VISIBLE);
            layoutLoading.setVisibility(View.GONE);
            FlowManager.init(new FlowConfig.Builder(getContext()).build());
            PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(10, new Gson().toJson(animes));
            playlistDetailItem.save();
        }
    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://pikasmart.com/api/Songs/recommend?limit=5&video_type=anime");
}else {
    FlowManager.init(new FlowConfig.Builder(getContext()).build());
    PlaylistDetailItem departments = new Select().from(PlaylistDetailItem.class).where(PlaylistDetailItem_Table.id.eq(10)).querySingle();
    try {
        JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
        for(int i = 0 ; i<array.length();i++)
            animes.add(new Gson().fromJson(array.get(i).toString(), VideoJSONObject.Song.class));
            infiniteScrollAdapter.notifyDataSetChanged();
        onItemChanged(animes.get(0));
        layout.setVisibility(View.VISIBLE);
        layoutLoading.setVisibility(View.GONE);
    } catch (JSONException e) {
        e.printStackTrace();
    }
}
    discreteScrollView.setItemTransitionTimeMillis(150);
    discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
            .setMinScale(0.8f)
            .setMaxScale(1.15f)
            .build());
    discreteScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
        @Override
        public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
            onItemChanged(animes.get(infiniteScrollAdapter.getRealPosition(adapterPosition)));
        }
    });
    }

    private void setUpAlbumAnime(View view) {
        final RecyclerView recyclerViewAlbum = view.findViewById(R.id.rv_anime_album);
        final AnimeAlbumAdapter albumAdapter = new AnimeAlbumAdapter(albums,getContext());
        LinearLayoutManager layoutManagerAlbum = new LinearLayoutManager(getContext());
        layoutManagerAlbum.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewAlbum.setLayoutManager(layoutManagerAlbum);
        recyclerViewAlbum.setAdapter(albumAdapter);
        final LinearLayout linearLayout = view.findViewById(R.id.place_holder_anime_album);
if(checkConnect.checkInternetConnection()) {
    new LoadListAlbums(albums, new CallBack() {
        @Override
        public void execute() {
            recyclerViewAlbum.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            albumAdapter.notifyDataSetChanged();
            FlowManager.init(new FlowConfig.Builder(getContext()).build());
            PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(102, new Gson().toJson(albums) );
            playlistDetailItem.save();

        }
    }).execute("http://pikasmart.com/api/albums/listAlbum?limit=5&skip=1&type_album=2");
}else {

    FlowManager.init(new FlowConfig.Builder(getContext()).build());
    PlaylistDetailItem departments = new Select().from(PlaylistDetailItem.class).where(PlaylistDetailItem_Table.id.eq(102)).querySingle();
    try {
        JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
        for(int i = 0 ; i<array.length();i++)
            albums.add(new Gson().fromJson(array.get(i).toString(), Album.class));
            albumAdapter.notifyDataSetChanged();
        recyclerViewAlbum.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    } catch (JSONException e) {
        e.printStackTrace();
    }
}

    }

    private void setUpViewPagerAnime(View view) {


        final ViewPager viewPager = view.findViewById(R.id.view_pager_indicator);
        if(checkConnect.checkInternetConnection()) {
            new loadListVideo(listPagers, new VideoCallBack() {
                @Override
                public void execute(ArrayList<VideoJSONObject.Song> songs) {
                    listPagers.addAll(songs);
                    ViewPagerAdapterChildImage viewPagerAdapterChildImage = new ViewPagerAdapterChildImage(getChildFragmentManager(), listPagers);
                    viewPager.setAdapter(viewPagerAdapterChildImage);
                    viewPager.setOffscreenPageLimit(5);
                    FlowManager.init(new FlowConfig.Builder(getContext()).build());
                    PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(5, new Gson().toJson(songs) );
                    playlistDetailItem.save();

                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://pikasmart.com/api/Songs/recommend?limit=5&video_type=music");

        }else{

            FlowManager.init(new FlowConfig.Builder(getContext()).build());
            PlaylistDetailItem departments = new Select().from(PlaylistDetailItem.class).where(PlaylistDetailItem_Table.id.eq(5)).querySingle();
            try {
                JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
                for(int i = 0 ; i<array.length();i++)
                    listPagers.add(new Gson().fromJson(array.get(i).toString(),VideoJSONObject.Song.class));
                ViewPagerAdapterChildImage viewPagerAdapterChildImage = new ViewPagerAdapterChildImage(getChildFragmentManager(), listPagers);
                viewPager.setAdapter(viewPagerAdapterChildImage);
                viewPager.setOffscreenPageLimit(5);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private void onItemChanged(VideoJSONObject.Song song) {
        tv_name_ja.setText(song.getName());
        tv_name_sub.setText(song.getNameEn());
    }
}
