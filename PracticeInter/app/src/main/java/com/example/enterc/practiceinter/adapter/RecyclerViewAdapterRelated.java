package com.example.enterc.practiceinter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Model.SongofSingerObject;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecyclerViewAdapterRelated  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<SongofSingerObject.Song> list;
    Context context;
    final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    public RecyclerViewAdapterRelated(ArrayList<SongofSingerObject.Song> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if(i == VIEW_TYPE_ITEM)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item_recycler_related,viewGroup,false);
            return new RecyclerViewHolderRelated(view);
        }
        else if(i == VIEW_TYPE_LOADING)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item_loading, viewGroup, false);
            return new LoadingViewHolder(view);
        }
         return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder r, final int i) {

        if(r instanceof  RecyclerViewHolderRelated)
        {
            SongofSingerObject.Song item = list.get(i);
            RecyclerViewHolderRelated viewHolder = (RecyclerViewHolderRelated) r;
            Glide.with(context).load(list.get(i).getThumbnail()).error(R.drawable.error).into(viewHolder.img);
            viewHolder.tv1.setText(list.get(i).getName());
            viewHolder.tv2.setText(list.get(i).getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayVideo.class);
                    String info   = new Gson().toJson(list.get(i));
                    intent.putExtra("INFO",info);
                    context.startActivity(intent);
                }
            });
        }
        else if(r instanceof LoadingViewHolder)
        {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)r;
            loadingViewHolder.progressBar.setIndeterminate(true);
          //  loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerViewHolderRelated extends RecyclerView.ViewHolder{
       ImageView img;
       TextView tv1;
       TextView tv2;
       ImageView imgMneu;
       TextView level;
        public RecyclerViewHolderRelated(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_item_related);
            tv1 = itemView.findViewById(R.id.tv_name_related);
            tv2 = itemView.findViewById(R.id.tv_name_singer_related);
            imgMneu = itemView.findViewById(R.id.img_item_menu);
            level = itemView.findViewById(R.id.tv_item_level);
        }
    }
    class LoadingViewHolder extends RecyclerView.ViewHolder
    {

        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM; // So sánh nếu item được get tại vị trí này là null thì view đó là loading view
    }
}
