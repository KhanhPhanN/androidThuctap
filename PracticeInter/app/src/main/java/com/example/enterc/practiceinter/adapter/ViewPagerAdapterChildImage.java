package com.example.enterc.practiceinter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.enterc.practiceinter.Fragment.FragmentImage;

public class ViewPagerAdapterChildImage extends FragmentPagerAdapter {
    public ViewPagerAdapterChildImage(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return FragmentImage.newInstance(i+1);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
