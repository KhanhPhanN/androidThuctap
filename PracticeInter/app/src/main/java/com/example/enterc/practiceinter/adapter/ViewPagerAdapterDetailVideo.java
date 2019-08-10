package com.example.enterc.practiceinter.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.enterc.practiceinter.Fragment.FragmentRelated;
import com.example.enterc.practiceinter.Fragment.FragmentVocabulary;
import com.example.enterc.practiceinter.FragmentAnime;
import com.example.enterc.practiceinter.FragmentDetailVideo1;

public class ViewPagerAdapterDetailVideo extends FragmentPagerAdapter {
    String key;
    String id;
    public ViewPagerAdapterDetailVideo(FragmentManager fm, String key, String id) {
        super(fm);
        this.key = key;
        this.id = id;
    }

    @Override
    public Fragment getItem(int i) {
        //FragmentDetailVideo1 fragmentDetailVideo1 = FragmentDetailVideo1.newInstance(key);
        switch (i){
            case 0: return FragmentDetailVideo1.newInstance(key);
            case 1: return FragmentVocabulary.newInstance(Integer.parseInt(id));
            case 2: return FragmentRelated.newInstance(id);
        }
       return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Lyrics";
            case 1: return "Vocabulary";
            case 2: return "Related";
        }
        return super.getPageTitle(position);
    }
}
