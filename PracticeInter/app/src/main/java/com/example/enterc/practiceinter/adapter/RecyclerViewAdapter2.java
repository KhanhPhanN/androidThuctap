package com.example.enterc.practiceinter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enterc.practiceinter.Model.Album;
import com.example.enterc.practiceinter.Model.AlbumJpop;
import com.example.enterc.practiceinter.R;

import java.util.ArrayList;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.RecyclerViewHolder>{
    ArrayList<AlbumJpop.Datum> list;
    Context context;

    public RecyclerViewAdapter2(ArrayList<AlbumJpop.Datum> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view               = inflater.inflate(R.layout.layout_recycler_2,viewGroup,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder r, int i) {
                 r.tv_name_album.setText(list.get(i).getName());
                 r.tv_count.setText(list.get(i).getCountVideo()+"videos");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
         RelativeLayout layout_recycler2;
         TextView tv_name_album;
         TextView tv_count;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_recycler2 = itemView.findViewById(R.id.rl_album);
            tv_name_album    = itemView.findViewById(R.id.tv_name_album);
            tv_count         = itemView.findViewById(R.id.tv_count);
        }
    }
}
