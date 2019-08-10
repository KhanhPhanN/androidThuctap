package com.example.enterc.practiceinter.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.enterc.practiceinter.Fragment.FragmentJpop;
import com.example.enterc.practiceinter.FragmentAnime;

public class ViewPagerAdapterChild extends FragmentPagerAdapter {
    public ViewPagerAdapterChild(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case  0: return new FragmentJpop();
            case  1: return new FragmentAnime();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Jpop";
            case 1: return "Anime";
        }
        return null;
    }
}
