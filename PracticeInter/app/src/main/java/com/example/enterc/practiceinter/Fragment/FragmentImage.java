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

import com.bumptech.glide.Glide;
import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.VideoCallBack;
import com.example.enterc.practiceinter.LoadData.loadListVideo;
import com.example.enterc.practiceinter.Model.Helper.CheckConnect;
import com.example.enterc.practiceinter.Model.Song;
import com.example.enterc.practiceinter.Model.VideoJSONObject;
import com.example.enterc.practiceinter.R;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FragmentImage extends Fragment {
    ArrayList<VideoJSONObject.Song> list = new ArrayList<>();
    private static final String KEY_COLOR = "key_color";
    public FragmentImage() {
    }
    public static FragmentImage newInstance(int color) {
        FragmentImage fragment = new FragmentImage();
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View viewImg       = inflater.inflate(R.layout.item_1,container,false);
        final ImageView img      = viewImg.findViewById(R.id.img_view);
        final TextView name      = viewImg.findViewById(R.id.tv_name);
        final TextView nametrans = viewImg.findViewById(R.id.tv_name_2);
        //String s ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQFS63fSc1QE37iSW3DGIAuoku4-vtQ0OOZ9iAn5EZxbZ3Tr4A";
        if(new CheckConnect(getContext()).checkInternetConnection()) {
            new loadListVideo(list, new VideoCallBack() {
                @Override
                public void execute(ArrayList<VideoJSONObject.Song> songs) {
                    list.addAll(songs);
                    switch (getArguments().getInt(KEY_COLOR)) {
                        case 1: {
                            Glide.with(getContext())
                                    .load(list.get(1).getThumbnail())
                                    .error(R.drawable.error)
                                    .into(img);
                            name.setText(list.get(1).getName());
                            nametrans.setText(list.get(1).getName());
                            viewImg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), PlayVideo.class);
                                    String ob      = new Gson().toJson(list.get(1));
                                    intent.putExtra("INFO",ob);
                                    startActivity(intent);
                                }
                            });
                        }
                        break;
                        case 2: {
                            Glide.with(getContext())
                                    .load(list.get(2).getThumbnail())
                                    .error(R.drawable.error)
                                    .into(img);
                            name.setText(list.get(2).getName());
                            nametrans.setText(list.get(2).getName());
                            viewImg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), PlayVideo.class);
                                    String ob      = new Gson().toJson(list.get(2));
                                    intent.putExtra("INFO",ob);
                                    startActivity(intent);
                                }
                            });
                        }
                        break;
                        case 3: {
                            Glide.with(getContext())
                                    .load(list.get(3).getThumbnail())
                                    .error(R.drawable.error)
                                    .into(img);
                            name.setText(list.get(3).getName());
                            nametrans.setText(list.get(3).getName());
                            viewImg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), PlayVideo.class);
                                    String ob      = new Gson().toJson(list.get(3));
                                    intent.putExtra("INFO",ob);
                                    startActivity(intent);
                                }
                            });
                        }
                        break;
                        case 4: {
                            Glide.with(getContext())
                                    .load(list.get(4).getThumbnail())
                                    .error(R.drawable.error)
                                    .into(img);
                            name.setText(list.get(4).getName());
                            nametrans.setText(list.get(4).getName());
                            viewImg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), PlayVideo.class);
                                    String ob      = new Gson().toJson(list.get(4));
                                    intent.putExtra("INFO",ob);
                                    startActivity(intent);
                                }
                            });
                        }
                        break;
                        case 5: {
                            Glide.with(getContext())
                                    .load(list.get(0).getThumbnail())
                                    .error(R.drawable.error)
                                    .into(img);
                            name.setText(list.get(0).getName());
                            nametrans.setText(list.get(0).getName());
                            viewImg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), PlayVideo.class);
                                    String ob      = new Gson().toJson(list.get(0));
                                    intent.putExtra("INFO",ob);
                                    startActivity(intent);
                                }
                            });
                        }
                        break;
                    }




                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://pikasmart.com/api/Songs/recommend?limit=5&video_type=music");
        }else{

        }
        return viewImg;
    }
}
