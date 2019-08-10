package com.example.enterc.practiceinter.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.enterc.practiceinter.Model.ChartJpopJSONObject;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecyclerViewAdapter4 extends RecyclerView.Adapter<RecyclerViewAdapter4.RecyclerViewHolder4>{
    ArrayList<ChartJpopJSONObject.Song> list;
    Context context;

    public RecyclerViewAdapter4(ArrayList<ChartJpopJSONObject.Song> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerViewHolder4 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
    if(i==0){
        view = inflater.inflate(R.layout.layout_recycler_41,viewGroup,false);
    }else{
        view = inflater.inflate(R.layout.layout_recycler_4,viewGroup,false);
    }
        return new RecyclerViewHolder4(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder4 r, final int i) {
        int stt = i+1;
        if(i<10){
            switch (i){
                case 1: {
                    r.txt_top.setText("0"+stt+"");
                    r.txt_top.setTextColor(Color.RED);
                }break;
                case  2: {
                    r.txt_top.setText("0"+stt+"");
                    r.txt_top.setTextColor(Color.GREEN);
                }break;
                case 3: {
                    r.txt_top.setText("0"+stt+"");
                    r.txt_top.setTextColor(R.color.colerrange);
                }break;
                default:   r.txt_top.setText("0"+stt+""); break;
            }

        }
        Glide.with(context).load(list.get(i).getThumbnail()).error(R.drawable.img_loading).into(r.img_top);
        r.txt_name.setText(list.get(i).getName());
        r.txt_name2.setText(list.get(i).getNameEn());
        r.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayVideo.class);
                String info   = new Gson().toJson(list.get(i));
                intent.putExtra("INFO",info);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RecyclerViewHolder4 extends RecyclerView.ViewHolder{
        TextView txt_top;
        ImageView img_top;
        TextView txt_name;
        TextView txt_name2;
        public RecyclerViewHolder4(@NonNull View itemView) {
            super(itemView);
            txt_top   = itemView.findViewById(R.id.tv_thutu);
            img_top   = itemView.findViewById(R.id.img_thutu);
            txt_name  = itemView.findViewById(R.id.tv_name_4);
            txt_name2 = itemView.findViewById(R.id.tv_eng_4);
        }
    }
}
