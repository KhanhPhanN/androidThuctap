package com.example.enterc.practiceinter.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enterc.practiceinter.HandlerThreadFurigana;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.example.enterc.practiceinter.Model.LyricSong;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.furiganaview.FuriganaView;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;

public class RecyclerViewAdapterLyric extends RecyclerView.Adapter<RecyclerViewAdapterLyric.RecyclerViewHolderLyric>  {
Context context;
ArrayList<LyricSong.Datum> list;
YouTubePlayer youTubePlayer;
HandlerThreadFurigana<RecyclerViewHolderLyric> mHandlerFurigana;
    public RecyclerViewAdapterLyric(Context context, ArrayList<LyricSong.Datum> list,YouTubePlayer youTubePlayer) {
        this.context = context;
        this.list = list;
        this.youTubePlayer = youTubePlayer;
       // this.mHandlerFurigana = mHandlerFurigana;
    }

    @NonNull
    @Override
    public RecyclerViewHolderLyric onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view               = inflater.inflate(R.layout.item_lyric_song,viewGroup,false);
        return new RecyclerViewHolderLyric(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderLyric r, final int i) {

        //r.tv_1.setText(list.get(i).getSentenceValue());
        r.tv_2.setText(list.get(i).getSentenceRo());
        LyricSong.Datum item = list.get(i);
        if (item.isSelected()) {
            //r.itemView.setBackgroundColor(R.color.colorgray);
            r.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorBackground));
            r.tv_2.setTextColor(context.getResources().getColor(R.color.colorgreen));
        } else {
            r.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorwhite));
            r.tv_2.setTextColor(context.getResources().getColor(R.color.colorgray));
        }
        if (item.getSentenceValue().isEmpty() && item.getSentenceHira().isEmpty())
            r.furiganaView.setVisibility(View.GONE);
        else {
            r.furiganaView.setVisibility(View.VISIBLE);
            mHandlerFurigana.queueConvertFurigana(r, item.getSentenceValue() + "|" + item.getSentenceHira());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

public class RecyclerViewHolderLyric extends RecyclerView.ViewHolder{
        //TextView tv_1;
        TextView tv_2;
        LinearLayout relativeLayout;
        FuriganaView furiganaView;
    public RecyclerViewHolderLyric(@NonNull View itemView) {
        super(itemView);
       // tv_1 = itemView.findViewById(R.id.tv_lyric_japan);
        tv_2 = itemView.findViewById(R.id.tv_lyric_eng);
        relativeLayout = itemView.findViewById(R.id.rl_video);
        furiganaView = itemView.findViewById(R.id.furigana_view);
        furiganaView.setTextFuriganaColor(context.getResources().getColor(R.color.colorgreen));
        furiganaView.setTextNormalColor(context.getResources().getColor(R.color.colorgreen));
        //TextPaint tp = createTextPaint();
        //furiganaView.text_set(tp, "ok", 1,1);

    }

    public FuriganaView getTitleFuriganaView() {
        return furiganaView;
    }
}
    public interface OnItemClickedListener{
        void onItemClicked();
    }
    private  OnItemClickedListener onItemClickedListener;
    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener){
        this.onItemClickedListener = onItemClickedListener;
    }


    public int getCurrentLyricSelected(int time) {
        int first = 0;
        int last = list.size() - 1;
        int mid = (first + last) / 2;
        while (first <= last) {
            int timeStart = StringHelper.ConvertStringToMillis(list.get(mid).getStartTime());
            int timeEnd = StringHelper.ConvertStringToMillis(list.get(mid).getEndTime());
            if (time >= timeStart && time <= timeEnd) {
                return mid;
            } else {
                if (time > timeEnd) {
                    first = mid + 1;
                } else {
                    last = mid - 1;
                }
            }
            mid = (first + last) / 2;
        }
        return -1;
    }


    public LyricSong.Datum getItem(int position) {
        if (position < list.size())
            return list.get(position);
        return null;
    }

    public void unSelected(int position) {
        if (position < list.size() && position >= 0 && list.get(position).isSelected()) {
            list.get(position).setSelected(false);
            notifyItemChanged(position);
        }
    }

    public void setSelected(int position) {
        if (position < list.size() && position >= 0) {
            list.get(position).setSelected(true);
            notifyItemChanged(position);
        }
    }
    private TextPaint createTextPaint() {
        TextPaint tp = new TextPaint();
        tp.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.textSize16));
        tp.setAntiAlias(true);
        tp.setColor(context.getResources().getColor(R.color.colorblack));
        tp.setDither(true);
        tp.setStyle(Paint.Style.FILL);
        tp.setStrokeJoin(Paint.Join.ROUND);
        return tp;
    }
    public void setmHandlerFurigana(HandlerThreadFurigana<RecyclerViewHolderLyric> l){
        this.mHandlerFurigana = l;
    }
}
