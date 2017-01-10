package com.wanglang.bottomnavigation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wanglang.bottomnavigation.fragments.FirstTopFragment;
import com.wanglang.bottomnavigation.fragments.SrcondFragment;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/11/11 0011.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments;
    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        int index = getIndex(position);
        Fragment fragment = fragments.get(position);
        if (fragment == null){
            if (position == 0){
                fragment = new FirstTopFragment();
            }else{
                fragment = new SrcondFragment();
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    public int getIndex(int pos){
        return pos % fragments.size();
    }
}
