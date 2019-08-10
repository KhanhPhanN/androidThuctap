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
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.Model.Song;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeNewAdapter extends RecyclerView.Adapter<AnimeNewAdapter.AnimeNewViewHolder> {
    ArrayList<VideoJSONObject.Song> newsAnime;
    Context context;

    public AnimeNewAdapter(ArrayList<VideoJSONObject.Song> newsAnime, Context context) {
        this.newsAnime = newsAnime;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeNewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_anime_new_3,viewGroup,false);
        return new AnimeNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeNewViewHolder animeNewViewHolder, final int i) {
        Glide.with(context).load(newsAnime.get(i).getThumbnail()).error(R.drawable.img_loading).into(animeNewViewHolder.iv_video);
        animeNewViewHolder.tv_name_ja.setText(newsAnime.get(i).getName());
        animeNewViewHolder.tv_name_sub.setText(newsAnime.get(i).getNameEn());
        animeNewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayVideo.class);
                String info   = new Gson().toJson(newsAnime.get(i));
                intent.putExtra("INFO",info);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsAnime.size();
    }

    class AnimeNewViewHolder extends RecyclerView.ViewHolder{
@BindView(R.id.iv_video)
        ImageView iv_video;
@BindView(R.id.tv_name_ja)
        TextView tv_name_ja;
@BindView(R.id.tv_name_sub)
TextView tv_name_sub;
        public AnimeNewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
