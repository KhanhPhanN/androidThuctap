package com.example.enterc.practiceinter.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.adapter.ViewPagerAdapterChild;

public class FragmentVideo extends Fragment {
    public FragmentVideo() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_video,container,false);
        ViewPagerAdapterChild viewPagerAdapterChild = new ViewPagerAdapterChild(getChildFragmentManager());
        TabLayout tabLayout = view.findViewById(R.id.tabs_video);
        ViewPager viewPager = view.findViewById(R.id.vp_video);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapterChild);
        return view;
    }
}
