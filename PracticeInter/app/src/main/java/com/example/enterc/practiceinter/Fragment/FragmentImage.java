package com.example.enterc.practiceinter.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Database.PlaylistDetailItem;
import com.example.enterc.practiceinter.Database.PlaylistDetailItem_Table;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.VideoCallBack;
import com.example.enterc.practiceinter.LoadData.loadListVideo;
import com.example.enterc.practiceinter.Model.AlbumJpop;
import com.example.enterc.practiceinter.Model.Helper.CheckConnect;
import com.example.enterc.practiceinter.Model.Song;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FragmentImage extends Fragment {
    ArrayList<VideoJSONObject.Song> list = new ArrayList<>();
    private static final String KEY_COLOR = "key_color";
    private static final String KEY_DATA = "key_data";

    public FragmentImage() {
    }

    public static FragmentImage newInstance(int color, ArrayList<VideoJSONObject.Song> list) {
        FragmentImage fragment = new FragmentImage();
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        args.putString(KEY_DATA, new Gson().toJson(list));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View viewImg = inflater.inflate(R.layout.item_1, container, false);
        final ImageView img = viewImg.findViewById(R.id.img_view);
        final TextView name = viewImg.findViewById(R.id.tv_name);
        final TextView nametrans = viewImg.findViewById(R.id.tv_name_2);
        final int i = getArguments().getInt(KEY_COLOR) - 1;
        String s = getArguments().getString(KEY_DATA);

//        VideoJSONObject videoJSONObject = new Gson().fromJson(s, VideoJSONObject.class);
//        list.addAll(videoJSONObject.getSong());
        try {
            JSONArray array = new JSONArray(s);
            for(int j=0;j<array.length();j++){
                String song = array.get(i).toString();
                list.add(new Gson().fromJson(song, VideoJSONObject.Song.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Glide.with(getContext())
                        .load(list.get(i).getThumbnail())
                        .error(R.drawable.error)
                        .into(img);
            name.setText(list.get(i).getName());
            nametrans.setText(list.get(i).getName());
            viewImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PlayVideo.class);
                    String ob = new Gson().toJson(list.get(i));
                    intent.putExtra("INFO", ob);
                    startActivity(intent);
                }
            });



        return viewImg;
    }
}
