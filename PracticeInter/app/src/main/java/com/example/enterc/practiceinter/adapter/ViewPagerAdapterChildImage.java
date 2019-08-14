package com.example.enterc.practiceinter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.enterc.practiceinter.Fragment.FragmentImage;
import com.example.enterc.practiceinter.Model.VideoJSONObject;

import java.util.ArrayList;

public class ViewPagerAdapterChildImage extends FragmentPagerAdapter {
    ArrayList<VideoJSONObject.Song> list;
    public ViewPagerAdapterChildImage(FragmentManager fm, ArrayList<VideoJSONObject.Song> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return FragmentImage.newInstance(i+1, list);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
