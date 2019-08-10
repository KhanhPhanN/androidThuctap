package com.example.enterc.practiceinter.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.LoadMore;
import com.example.enterc.practiceinter.LoadData.LoadListSongofSinger;

import com.example.enterc.practiceinter.Model.SongofSingerObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapterRelated;

import java.util.ArrayList;

public class FragmentRelated extends Fragment {
    ArrayList<SongofSingerObject.Song> list = new ArrayList<>();
    String id;
    SongofSingerObject singerObject = new SongofSingerObject();
    Boolean isLoading=false;
    int visibleThreshold=25;
    int lastVisibleItem,totalItemCount;
    LoadMore loadMore;
    public static FragmentRelated newInstance(String id){
        Bundle args = new Bundle();
        args.putString("id", id);
        FragmentRelated fragment = new FragmentRelated();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_layout_related,container,false);



        Bundle bundle = getArguments();
        if (bundle != null)
            id = bundle.getString("id");


        final RecyclerView recyclerView = view.findViewById(R.id.rc_related);
        final RecyclerViewAdapterRelated adapterRelated = new RecyclerViewAdapterRelated(list,getContext());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRelated);


        loadMore = new LoadMore() {
            @Override
            public void onLoadMore() {
                new LoadListSongofSinger(singerObject, list, new CallBack() {
                    @Override
                    public void execute() {
                        adapterRelated.notifyDataSetChanged();
                        setLoaded();
                    }
                }).execute("http://pikasmart.com/api/Songs/related?id_singer="+id+"&limit=5&skip=2&video_type=music");
            }
        };
        new LoadListSongofSinger(singerObject,list, new CallBack() {
            @Override
            public void execute() {
                 //Log.d("abcd",list.size()+"");
               adapterRelated.notifyDataSetChanged();
            }
        }).execute("http://pikasmart.com/api/Songs/related?id_singer="+id+"&limit=5&skip=2&video_type=music");


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount(); // Lấy tổng số lượng item đang có
                lastVisibleItem = layoutManager.findLastVisibleItemPosition(); // Lấy vị trí của item cuối cùng
                if(!isLoading&&totalItemCount <= (lastVisibleItem+visibleThreshold)) // Nếu không phải trạng thái loading và tổng số lượng item bé hơn hoặc bằng vị trí item cuối + số lượng item tối đa hiển thị
                {
                    loadMore.onLoadMore();
                    isLoading = true;
                }

            }
        });

        return view;
    }
    public void setLoaded() {
        isLoading = false;
    }
}
