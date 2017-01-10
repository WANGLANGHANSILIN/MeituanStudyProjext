package com.wanglang.bottomnavigation.adapters;

        import android.support.v4.view.PagerAdapter;
        import android.view.View;
        import android.view.ViewGroup;

        import java.util.ArrayList;

/**
 * Created by wangl on 2016/11/24 0024.
 */

public class MineVewPagerAdapter extends PagerAdapter {
    private ArrayList<View> mViews;

    public MineVewPagerAdapter(ArrayList<View> views) {
        mViews = views;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


//        return super.instantiateItem(container, position);
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(mViews.get(position));
    }
}
