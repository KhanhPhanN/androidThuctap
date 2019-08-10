package com.example.enterc.practiceinter.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Model.Album;
import com.example.enterc.practiceinter.Model.NewestJpopJSONOject;
import com.example.enterc.practiceinter.Model.TopViewJSONObject;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecyclerViewAdapter5 extends RecyclerView.Adapter<RecyclerViewAdapter5.RecyclerViewHolder5>{
    ArrayList<TopViewJSONObject.Song> songs;
    Context context;

    public RecyclerViewAdapter5(ArrayList<TopViewJSONObject.Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder5 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_recycler_3,viewGroup,false);
        return new RecyclerViewHolder5(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder5 r, final int i) {
        Glide.with(context).load(songs.get(i).getThumbnail()).error(R.drawable.img_loading).into(r.img);
        r.tv_name_japanese.setText(songs.get(i).getName());
        r.tv_name_english.setText(songs.get(i).getNameEn());
        r.tv_luot_xem.setText(songs.get(i).getView()+"");
        r.tv_count.setText(songs.get(i).getVideoLength());
        r.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayVideo.class);
                String info   = new Gson().toJson(songs.get(i));
                intent.putExtra("INFO",info);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class RecyclerViewHolder5 extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv_name_japanese;
        TextView tv_name_english;
        TextView tv_luot_xem;
        TextView tv_count;
        public RecyclerViewHolder5(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_top_newest);
            tv_name_japanese = itemView.findViewById(R.id.tv_name_japan);
            tv_name_english  = itemView.findViewById(R.id.tv_name_eng);
            tv_luot_xem      = itemView.findViewById(R.id.tv_luot_xem);
            tv_count         = itemView.findViewById(R.id.tv_time_count);
        }
    }
}
