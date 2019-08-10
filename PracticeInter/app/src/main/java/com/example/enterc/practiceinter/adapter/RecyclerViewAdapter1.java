package com.example.enterc.practiceinter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Model.Helper.ImageConverter;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.Model.Singer;

import java.util.ArrayList;

public class RecyclerViewAdapter1 extends RecyclerView.Adapter<RecyclerViewAdapter1.RViewHolder>{
    Context context;
    ArrayList<Singer> list;
    public RecyclerViewAdapter1(Context context, ArrayList<Singer> list) {
        this.context = context;
        this.list    = list;
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_recyclerview_1,viewGroup,false);
        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder rViewHolder, int i) {
        Bitmap bitmap;
        Glide.with(context)
                .load(list.get(i).getAvatar())
                .error(R.drawable.error)
                .into(rViewHolder.img);
        rViewHolder.txt.setText(list.get(i).getName());
        BitmapDrawable drawable = (BitmapDrawable) rViewHolder.img.getDrawable();
        if(drawable!=null){
             bitmap = drawable.getBitmap();
        }else{
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.pipop);
        }
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
        rViewHolder.img.setImageBitmap(circularBitmap);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RViewHolder extends RecyclerView.ViewHolder{
          ImageView img;
          TextView txt;
        public RViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_avatar_singer);
            txt = itemView.findViewById(R.id.tv_name_singer);
        }
    }
}
