package com.example.enterc.practiceinter;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.ItemChangeListener;
import com.example.enterc.practiceinter.LoadData.loadListLyricSong;
import com.example.enterc.practiceinter.Model.LyricSong;
import com.example.enterc.practiceinter.adapter.RecyclerViewAdapterLyric;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDetailVideo1 extends Fragment implements ItemChangeListener {
    @BindView(R.id.rv_listLanguae)
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    RecyclerViewAdapterLyric adapter2;
    ArrayList<LyricSong.Datum> x = new ArrayList<>();
    YouTubePlayer youTubePlayer;
    String key;
    HandlerThreadFurigana mHandlerFurigana;
    TextPaint tp;
    int timeStartLyric = -1, timeEndLyric = -1, lastPositionSelected = -1, lastTimeEnd = -1, lastTimeStart = -1;

    public FragmentDetailVideo1() {
    }

    public static FragmentDetailVideo1 newInstance(String key){
        Bundle args = new Bundle();
        args.putString("KEY", key);
        FragmentDetailVideo1 fragment = new FragmentDetailVideo1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager_video, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null)
            key = bundle.getString("KEY");
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setupHandlerFurigana();
        adapter2 = new RecyclerViewAdapterLyric(getContext(), x, youTubePlayer);
        adapter2.setmHandlerFurigana(mHandlerFurigana);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter2);

        final LyricSong list = new LyricSong();
        new loadListLyricSong(x, new CallBack() {
            @Override
            public void execute() {
                adapter2.notifyDataSetChanged();
            }
        }).execute("http://pikasmart.com/api/SongSentences/getListLyricsBylanguageCode?song_id=" + key);


    }

    public void setupHandlerFurigana() {
        // create text paint for furigana view
        tp = createTextPaint();
        // start convert furigana
        Handler responseHandler = new Handler();
        mHandlerFurigana = new HandlerThreadFurigana<>(responseHandler, getContext());
        mHandlerFurigana.setHandlerFuriganaListener(new HandlerThreadFurigana.HandlerFuriganaListener<RecyclerViewAdapterLyric.RecyclerViewHolderLyric>() {
            @Override
            public void onFuriganaConverted(RecyclerViewAdapterLyric.RecyclerViewHolderLyric target, String converted) {
                if (target != null && converted != null) {
                    target.getTitleFuriganaView().text_set(tp, converted, -1, -1);
                }
            }
        });
        mHandlerFurigana.start();
        mHandlerFurigana.getLooper();
    }

    // MARK: init text paint
    private TextPaint createTextPaint() {
        TextPaint tp = new TextPaint();
        tp.setTextSize(getResources().getDimensionPixelSize(R.dimen.textSize16));
        tp.setAntiAlias(true);
        tp.setColor(getResources().getColor(R.color.colorblack));
        tp.setDither(true);
        tp.setStyle(Paint.Style.FILL);
        tp.setStrokeJoin(Paint.Join.ROUND);
        return tp;
    }

    @Override
    public void onCurrentTime(int time) {
        if (adapter2 == null)
            return;
        if (time < timeStartLyric) { //đầu video khi chưa có lyric hoặc kết thúc video
            Log.d("Khanh", "1");
            if (lastPositionSelected != -1) {
                adapter2.unSelected(lastPositionSelected);
                lastPositionSelected = -1;
                lastTimeEnd = -1;
                lastTimeStart = -1;
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
            return;
        }

        if (time < lastTimeEnd && time >= lastTimeStart)
        {
            Log.d("Khanh", "1");
            return; // vẫn trong thời gian của câu hiện tại
        }


        LyricSong.Datum datum = adapter2.getItem(lastPositionSelected + 1);
        if (datum != null) {
            int timeStart = StringHelper.ConvertStringToMillis(datum.getStartTime());
            int timeEnd = StringHelper.ConvertStringToMillis(datum.getEndTime());
            Log.d("TEST", timeStart + "---" + timeEnd);
            if (time >= timeStart && time < timeEnd) { // nếu là câu tiếp theo
                if (lastPositionSelected != -1) adapter2.unSelected(lastPositionSelected);
                adapter2.setSelected(++lastPositionSelected);
                layoutManager.scrollToPositionWithOffset(lastPositionSelected, 1);
                lastTimeStart = timeStart;
                lastTimeEnd = timeEnd;
                return;
            }
        }
        // TH còn lại: không phải câu tiếp theo, không phải tg đầu và cuối khi ko có lyric, không phải trong khoảng tg của câu hiện tại
        searchAndSetLyricCurrent(time);
    }

    private void searchAndSetLyricCurrent(int time) {
        if (lastPositionSelected != -1) adapter2.unSelected(lastPositionSelected);
        int pos = adapter2.getCurrentLyricSelected(time);
        if (pos != -1 && pos < adapter2.getItemCount()) {
            adapter2.setSelected(pos);
            lastPositionSelected = pos;
            LyricSong.Datum datum = adapter2.getItem(lastPositionSelected);
            lastTimeStart = StringHelper.ConvertStringToMillis(datum.getStartTime());
            lastTimeEnd = StringHelper.ConvertStringToMillis(datum.getEndTime());
            layoutManager.scrollToPositionWithOffset(lastPositionSelected, 1);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity != null) {
            if (activity instanceof PlayVideo) {
                ((PlayVideo) activity).setLyricItemClickListener(this);
            }
        }
    }
}
