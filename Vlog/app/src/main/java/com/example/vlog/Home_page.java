package com.example.vlog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Home_page extends Fragment {
    private View view;
    private Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter mPagerAdapter;
    private String[] titles = {"关注","推荐","话题"};
    private List<Fragment> fragments;
    private Fragment nowfragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_page, container,false);
        context = getActivity();
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        init();
        return view;
    }
    private void init(){
        fragments = new ArrayList<>();
        fragments.add(new Attention_page());
        fragments.add(new Recommendation_page());
        fragments.add(new Topic_page());
        mPagerAdapter = new PagerAdapter(getChildFragmentManager(),titles,fragments,context);
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
