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
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeAlbumAdapter extends RecyclerView.Adapter<AnimeAlbumAdapter.AnimeAlbumViewHolder>{
    List<Album> albums;
    Context context;
    public AnimeAlbumAdapter(List<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.layout_item_album_anime,viewGroup,false);
        return new AnimeAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeAlbumViewHolder holder, final int i) {
        Glide.with(context).load(albums.get(i).getLink_img_album()).error(R.drawable.img_loading).into(holder.iv_album);
        holder.tv_name.setText(albums.get(i).getName_album());
        holder.tv_count.setText(albums.get(i).getCount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayVideo.class);
                String info   = new Gson().toJson(albums.get(i));
                intent.putExtra("INFO",info);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    class AnimeAlbumViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_album)
        ImageView iv_album;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_count)
        TextView tv_count;
        public AnimeAlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
