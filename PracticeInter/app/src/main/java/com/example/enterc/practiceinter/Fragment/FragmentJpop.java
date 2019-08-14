package com.example.enterc.practiceinter.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Database.PlaylistDetailItem;
import com.example.enterc.practiceinter.Database.PlaylistDetailItem_Table;
import com.example.enterc.practiceinter.Database.TypeVideoItem;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.PiChartCallBack;
import com.example.enterc.practiceinter.Listener.SingerCallBack;
import com.example.enterc.practiceinter.Listener.SongsCallBack;
import com.example.enterc.practiceinter.Listener.TopViewJpopCallBack;
import com.example.enterc.practiceinter.Listener.VideoCallBack;
import com.example.enterc.practiceinter.LoadData.LoadListAlbumJpop;
import com.example.enterc.practiceinter.LoadData.LoadListNewestJpop;
import com.example.enterc.practiceinter.LoadData.LoadListPiChartJpop;
import com.example.enterc.practiceinter.LoadData.LoadListTopViewJpop;
import com.example.enterc.practiceinter.LoadData.loadListSinger;
import com.example.enterc.practiceinter.LoadData.loadListVideo;
import com.example.enterc.practiceinter.Model.Album;
import com.example.enterc.practiceinter.Model.AlbumJpop;
import com.example.enterc.practiceinter.Model.ChartJpopJSONObject;
import com.example.enterc.practiceinter.Model.Helper.CheckConnect;
import com.example.enterc.practiceinter.Model.NewestJpopJSONOject;
import com.example.enterc.practiceinter.Model.Singer;
import com.example.enterc.practiceinter.Model.TopViewJSONObject;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter5;
import com.example.enterc.practiceinter.adapter.ViewPagerAdapterChildImage;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter1;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter2;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter3;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter4;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.getDefaultSize;

public class FragmentJpop extends Fragment {
    ArrayList<Singer> list;
    ArrayList<AlbumJpop.Datum> albumJpops;
    ArrayList<NewestJpopJSONOject.Song> newestJpops;
    ArrayList<ChartJpopJSONObject.Song> piChartJsops;
    ArrayList<TopViewJSONObject.Song> topViewJpops;
    ArrayList<VideoJSONObject.Song> listPagers;
    CheckConnect checkConnect;
    public FragmentJpop() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewJpop = inflater.inflate(R.layout.layout_jpop, container, false);
        list         = new ArrayList<>();
        albumJpops   = new ArrayList<>();
        newestJpops  = new ArrayList<>();
        piChartJsops = new ArrayList<>();
        topViewJpops = new ArrayList<>();
        listPagers   = new ArrayList<>();
        checkConnect = new CheckConnect(getContext());



    setUpViewPager(viewJpop);
    setUpRecyclerViewListSinger(viewJpop);
    setUpRecyclerViewAlbum(viewJpop);
    setUpRecyclerViewNewest(viewJpop);
    setUpRecyclerViewPiChart(viewJpop);
    srtUpRecyclerViewTop(viewJpop);

           return viewJpop;
        }


    public void setUpViewPager(View view){
        final ViewPager viewPager = view.findViewById(R.id.vp_jpop);
        if(checkConnect.checkInternetConnection()) {
            new loadListVideo(listPagers, new VideoCallBack() {
                @Override
                public void execute(ArrayList<VideoJSONObject.Song> songs) {
                    listPagers.addAll(songs);
                    ViewPagerAdapterChildImage viewPagerAdapterChildImage = new ViewPagerAdapterChildImage(getChildFragmentManager(), listPagers);
                    viewPager.setAdapter(viewPagerAdapterChildImage);
                    viewPager.setOffscreenPageLimit(5);
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
    public void srtUpRecyclerViewTop(View viewJpop) {


        final LinearLayout layout_rv_hot = viewJpop.findViewById(R.id.layout_rv_hot);
        final LinearLayout place_holder_anime_hot = viewJpop.findViewById(R.id.place_holder_anime_hot);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 2);
        RecyclerView recyclerView5 = viewJpop.findViewById(R.id.rv_hot);
        final RecyclerViewAdapter5 adapter5 = new RecyclerViewAdapter5(topViewJpops, getContext());
        recyclerView5.setLayoutManager(gridLayoutManager1);
        recyclerView5.setAdapter(adapter5);

if(new CheckConnect(getContext()).checkInternetConnection()){
    new LoadListTopViewJpop(topViewJpops, new TopViewJpopCallBack() {
        @Override
        public void execute(ArrayList<TopViewJSONObject.Song> songs) {
            topViewJpops.addAll(songs);
            adapter5.notifyDataSetChanged();
            place_holder_anime_hot.setVisibility(GONE);
            layout_rv_hot.setVisibility(View.VISIBLE);
                FlowManager.init(new FlowConfig.Builder(getContext()).build());
                PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(new Gson().toJson(songs) );
                playlistDetailItem.save();
        }
    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/Songs/getTopViewVideoPerNew?video_type=music&limit=6&type=0&skip=0");
}else{
    FlowManager.init(new FlowConfig.Builder(getContext()).build());
    PlaylistDetailItem departments = new Select().from(PlaylistDetailItem.class).querySingle();
    try {
        JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
        for(int i = 0 ; i<array.length();i++)
        topViewJpops.add(new Gson().fromJson(array.get(i).toString(), TopViewJSONObject.Song.class));
        adapter5.notifyDataSetChanged();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    place_holder_anime_hot.setVisibility(GONE);
    layout_rv_hot.setVisibility(View.VISIBLE);
}

    }
    public void setUpRecyclerViewListSinger(View view){
            final RecyclerView recyclerView = view.findViewById(R.id.rc_jpop_1);
            RecyclerViewAdapter1 adapter1 = new RecyclerViewAdapter1(getContext(), list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter1);
            if (new CheckConnect(getContext()).checkInternetConnection()) {
                new loadListSinger(list, new SingerCallBack() {
                    @Override
                    public void execute(ArrayList<Singer> singers) {
                        list.addAll(singers);
                        recyclerView.getAdapter().notifyDataSetChanged();
                FlowManager.init(new FlowConfig.Builder(getContext()).build());
                PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(13 , new Gson().toJson(singers) );
                playlistDetailItem.save();
                    }
                }).execute("http://pikasmart.com/api/Singers/listsinger?limit=6");
            } else {


                FlowManager.init(new FlowConfig.Builder(getContext()).build());
                PlaylistDetailItem departments = new Select().from(PlaylistDetailItem.class).where(PlaylistDetailItem_Table.id.eq(13)).querySingle();
                try {
                    JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
                    for(int i = 0 ; i<array.length();i++)
                       list.add(new Gson().fromJson(array.get(i).toString(), Singer.class));
                       adapter1.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }




//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef   = database.getReference("ListFolow");
//            myRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                        String key = snapshot.getKey();
//                        String value  = snapshot.getValue().toString();
//                        HashMap<String,Object> hashMap= (HashMap<String, Object>) dataSnapshot.getValue();
//                        Log.d("AAA",hashMap.get("name").toString());
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
            }
        }
    public void setUpRecyclerViewAlbum(View view){
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            final RecyclerViewAdapter2 adapter2 = new RecyclerViewAdapter2(albumJpops, getContext());
            RecyclerView recyclerView2 = view.findViewById(R.id.rc_jpop_2);
            recyclerView2.setLayoutManager(layoutManager2);
            recyclerView2.setAdapter(adapter2);

if(new CheckConnect(getContext()).checkInternetConnection()){

    new LoadListAlbumJpop(albumJpops, new CallBack() {
        @Override
        public void execute() {
             adapter2.notifyDataSetChanged();
            FlowManager.init(new FlowConfig.Builder(getContext()).build());
            PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(10, new Gson().toJson(albumJpops) );
            playlistDetailItem.save();
        }
    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/albums/listAlbum?limit=5&skip=0&type_album=1");

}else{
    FlowManager.init(new FlowConfig.Builder(getContext()).build());
    PlaylistDetailItem departments = new Select().from(PlaylistDetailItem.class).where(PlaylistDetailItem_Table.id.eq(10)).querySingle();
    try {
        JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
        for(int i = 0 ; i<array.length();i++)
            albumJpops.add(new Gson().fromJson(array.get(i).toString(), AlbumJpop.Datum.class));
            adapter2.notifyDataSetChanged();
    } catch (JSONException e) {
            e.printStackTrace();
    }

}
        }
    public void setUpRecyclerViewNewest(View view) {

        final LinearLayout place_holder_anime_new = view.findViewById(R.id.place_holder_anime_new);
        final LinearLayout btn_seen_more_new = view.findViewById(R.id.btn_seen_more_new);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        final RecyclerView recyclerView3 = view.findViewById(R.id.rc_jpop_3);
        final RecyclerViewAdapter3 adapter3 = new RecyclerViewAdapter3(newestJpops, getContext());
        recyclerView3.setLayoutManager(gridLayoutManager);
        recyclerView3.setAdapter(adapter3);
        if (checkConnect.checkInternetConnection()) {
            new LoadListNewestJpop(newestJpops, new SongsCallBack() {
                @Override
                public void execute(ArrayList<NewestJpopJSONOject.Song> songs) {
                    newestJpops.addAll(songs);
                    adapter3.notifyDataSetChanged();
                    place_holder_anime_new.setVisibility(GONE);
                    btn_seen_more_new.setVisibility(View.VISIBLE);
                    recyclerView3.setVisibility(View.VISIBLE);
            FlowManager.init(new FlowConfig.Builder(getContext()).build());
            PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(11, new Gson().toJson(songs) );
            playlistDetailItem.save();
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://pikasmart.com/api/Songs/listnew?limit=6&skip=1&video_type=music");

        }else{
            FlowManager.init(new FlowConfig.Builder(getContext()).build());
            PlaylistDetailItem departments = new Select()
                    .from(PlaylistDetailItem.class)
                    .where(PlaylistDetailItem_Table.id.eq(11))
                    .querySingle();
            try {
                JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
                for(int i = 0 ; i<array.length();i++)
                    newestJpops.add(new Gson().fromJson(array.get(i).toString(), NewestJpopJSONOject.Song.class));
                    adapter3.notifyDataSetChanged();
                place_holder_anime_new.setVisibility(GONE);
                btn_seen_more_new.setVisibility(View.VISIBLE);
                recyclerView3.setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    public void setUpRecyclerViewPiChart(View upRecyclerViewPiChart) {
            final LinearLayout place_holder_pichart = upRecyclerViewPiChart.findViewById(R.id.place_holder_pichart);

            LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            layoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
            final RecyclerViewAdapter4 adapter4 = new RecyclerViewAdapter4(piChartJsops, getContext());
            final RecyclerView recyclerView4 = upRecyclerViewPiChart.findViewById(R.id.rc_jpop_4);
            recyclerView4.setLayoutManager(layoutManager4);
            recyclerView4.setAdapter(adapter4);

        if(checkConnect.checkInternetConnection())
            new LoadListPiChartJpop(piChartJsops, new PiChartCallBack() {
                @Override
                public void execute(ArrayList<ChartJpopJSONObject.Song> songs) {
                    piChartJsops.addAll(songs);
                    adapter4.notifyDataSetChanged();
                    recyclerView4.setVisibility(View.VISIBLE);
                    place_holder_pichart.setVisibility(GONE);
                    FlowManager.init(new FlowConfig.Builder(getContext()).build());
                    PlaylistDetailItem playlistDetailItem = new PlaylistDetailItem(12, new Gson().toJson(songs) );
                    playlistDetailItem.save();
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/Songs/topViewVideo?video_type=music&limit=6&type=1&skip=1");
else {
    FlowManager.init(new FlowConfig.Builder(getContext()).build());
    PlaylistDetailItem departments = new Select()
            .from(PlaylistDetailItem.class)
            .where(PlaylistDetailItem_Table.id.eq(12))
            .querySingle();
    try {
        JSONArray array = new JSONArray(departments.getSongsJSONData()+"");
        for (int i = 0; i < array.length(); i++)
            piChartJsops.add(new Gson().fromJson(array.get(i).toString(), ChartJpopJSONObject.Song.class));
        adapter4.notifyDataSetChanged();
        recyclerView4.setVisibility(View.VISIBLE);
        place_holder_pichart.setVisibility(GONE);
    } catch (JSONException e) {
        e.printStackTrace();
    }
}
}
}
