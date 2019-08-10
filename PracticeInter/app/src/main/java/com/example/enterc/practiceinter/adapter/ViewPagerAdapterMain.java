package com.example.enterc.practiceinter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.enterc.practiceinter.Fragment.FragmentAccount;
import com.example.enterc.practiceinter.Fragment.FragmentMore;
import com.example.enterc.practiceinter.Fragment.FragmentNewsFeed;
import com.example.enterc.practiceinter.Fragment.FragmentVideo;

public class ViewPagerAdapterMain extends FragmentPagerAdapter {
    public ViewPagerAdapterMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new FragmentVideo();
            case 1: return new FragmentAccount();
            case 2: return new FragmentNewsFeed();
            case 3: return new FragmentMore();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
