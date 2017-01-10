package com.wanglang.bottomnavigation.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/11/9 0009.
 */

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> mViews;
    public MyPagerAdapter(ArrayList<View> mViews) {
        this.mViews = mViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int index = getIndex(position);
        View view = mViews.get(index);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int index = getIndex(position);
        container.removeView(mViews.get(index));
    }

    public int getIndex(int position){
        return position % mViews.size();
    }
}
