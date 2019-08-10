package com.example.enterc.practiceinter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.Model.Song;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeHotAdapter extends RecyclerView.Adapter<AnimeHotAdapter.AnimeHotViewHolder> {
    ArrayList<VideoJSONObject.Song> songs;
    Context context;

    public AnimeHotAdapter(ArrayList<VideoJSONObject.Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeHotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view               = inflater.inflate(R.layout.item_anime_hot,viewGroup,false);
        return new AnimeHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeHotViewHolder holder, final int i) {
        Glide.with(context).load(songs.get(i).getThumbnail()).error(R.drawable.img_loading).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

    class AnimeHotViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_video)
        ImageView imageView;
        public AnimeHotViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
