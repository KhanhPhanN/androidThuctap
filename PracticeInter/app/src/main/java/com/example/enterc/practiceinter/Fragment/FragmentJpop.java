package com.example.enterc.practiceinter.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.PiChartCallBack;
import com.example.enterc.practiceinter.Listener.SongsCallBack;
import com.example.enterc.practiceinter.Listener.TopViewJpopCallBack;
import com.example.enterc.practiceinter.LoadData.LoadListAlbumJpop;
import com.example.enterc.practiceinter.LoadData.LoadListNewestJpop;
import com.example.enterc.practiceinter.LoadData.LoadListPiChartJpop;
import com.example.enterc.practiceinter.LoadData.LoadListTopViewJpop;
import com.example.enterc.practiceinter.LoadData.loadListSinger;
import com.example.enterc.practiceinter.Model.Album;
import com.example.enterc.practiceinter.Model.AlbumJpop;
import com.example.enterc.practiceinter.Model.ChartJpopJSONObject;
import com.example.enterc.practiceinter.Model.Helper.CheckConnect;
import com.example.enterc.practiceinter.Model.NewestJpopJSONOject;
import com.example.enterc.practiceinter.Model.Singer;
import com.example.enterc.practiceinter.Model.TopViewJSONObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter5;
import com.example.enterc.practiceinter.adapter.ViewPagerAdapterChildImage;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter1;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter2;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter3;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapter4;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class FragmentJpop extends Fragment {
    ArrayList<Singer> list;
    ArrayList<AlbumJpop.Datum> albumJpops;
    ArrayList<NewestJpopJSONOject.Song> newestJpops;
    ArrayList<ChartJpopJSONObject.Song> piChartJsops;
    ArrayList<TopViewJSONObject.Song> topViewJpops;
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



            setUpViewPager(viewJpop);
            setUpRecyclerViewListSinger(viewJpop);
            setUpRecyclerViewAlbum(viewJpop);
            setUpRecyclerViewNewest(viewJpop);
            setUpRecyclerViewPiChart(viewJpop);
            srtUpRecyclerViewTop(viewJpop);


           return viewJpop;
        }

    private void srtUpRecyclerViewTop(View viewJpop) {
        final LinearLayout layout_rv_hot = viewJpop.findViewById(R.id.layout_rv_hot);
        final LinearLayout place_holder_anime_hot = viewJpop.findViewById(R.id.place_holder_anime_hot);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 2);
        RecyclerView recyclerView5 = viewJpop.findViewById(R.id.rv_hot);
        final RecyclerViewAdapter5 adapter5 = new RecyclerViewAdapter5(topViewJpops, getContext());
        recyclerView5.setLayoutManager(gridLayoutManager1);
        recyclerView5.setAdapter(adapter5);


        new LoadListTopViewJpop(topViewJpops, new TopViewJpopCallBack() {
            @Override
            public void execute(ArrayList<TopViewJSONObject.Song> songs) {
                topViewJpops.addAll(songs);
                adapter5.notifyDataSetChanged();
                place_holder_anime_hot.setVisibility(GONE);
                layout_rv_hot.setVisibility(View.VISIBLE);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/Songs/getTopViewVideoPerNew?video_type=music&limit=6&type=0&skip=0");

    }

    public void setUpViewPager(View view){
            final ViewPager viewPager = view.findViewById(R.id.vp_jpop);
            ViewPagerAdapterChildImage viewPagerAdapterChildImage = new ViewPagerAdapterChildImage(getChildFragmentManager());
            viewPager.setAdapter(viewPagerAdapterChildImage);
            viewPager.setOffscreenPageLimit(5);
        }

    public void setUpRecyclerViewListSinger(View view){
            final RecyclerView recyclerView = view.findViewById(R.id.rc_jpop_1);
            RecyclerViewAdapter1 adapter1 = new RecyclerViewAdapter1(getContext(), list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter1);
            if (new CheckConnect(getContext()).checkInternetConnection()) {
                new loadListSinger(list, new CallBack() {
                    @Override
                    public void execute() {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }).execute("http://pikasmart.com/api/Singers/listsinger?limit=6");
            } else {
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


            new LoadListAlbumJpop(albumJpops, new CallBack() {
                @Override
                public void execute() {
                    adapter2.notifyDataSetChanged();
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/albums/listAlbum?limit=5&skip=0&type_album=1");


        }


    public  void setUpRecyclerViewNewest(View view){

            final LinearLayout place_holder_anime_new = view.findViewById(R.id.place_holder_anime_new);
            final LinearLayout btn_seen_more_new = view.findViewById(R.id.btn_seen_more_new);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            final RecyclerView recyclerView3 = view.findViewById(R.id.rc_jpop_3);
            final RecyclerViewAdapter3 adapter3 = new RecyclerViewAdapter3(newestJpops, getContext());
            recyclerView3.setLayoutManager(gridLayoutManager);
            recyclerView3.setAdapter(adapter3);

            new LoadListNewestJpop(newestJpops, new SongsCallBack() {
                @Override
                public void execute(ArrayList<NewestJpopJSONOject.Song> songs) {
                    newestJpops.addAll(songs);
                    adapter3.notifyDataSetChanged();
                    place_holder_anime_new.setVisibility(GONE);
                    btn_seen_more_new.setVisibility(View.VISIBLE);
                    recyclerView3.setVisibility(View.VISIBLE);
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/Songs/listnew?limit=6&skip=1&video_type=music");

        }

    public void setUpRecyclerViewPiChart(View upRecyclerViewPiChart) {
            final LinearLayout place_holder_pichart = upRecyclerViewPiChart.findViewById(R.id.place_holder_pichart);

            LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            layoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
            final RecyclerViewAdapter4 adapter4 = new RecyclerViewAdapter4(piChartJsops, getContext());
            final RecyclerView recyclerView4 = upRecyclerViewPiChart.findViewById(R.id.rc_jpop_4);
            recyclerView4.setLayoutManager(layoutManager4);
            recyclerView4.setAdapter(adapter4);


            new LoadListPiChartJpop(piChartJsops, new PiChartCallBack() {
                @Override
                public void execute(ArrayList<ChartJpopJSONObject.Song> songs) {
                    piChartJsops.addAll(songs);
                    adapter4.notifyDataSetChanged();
                    recyclerView4.setVisibility(View.VISIBLE);
                    place_holder_pichart.setVisibility(GONE);
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/Songs/topViewVideo?video_type=music&limit=6&type=1&skip=1");

        }
}
