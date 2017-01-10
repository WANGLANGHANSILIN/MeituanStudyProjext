package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.entitys.CategrayTagEntity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Around_AllFragment extends Fragment {




    private String[] mTagNames = {"热门","面包甜点","小吃快餐","北京菜","川菜","日本料理"};
    private ArrayList<CategrayTagEntity> mArrTags;

    public Around_AllFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_around_all, container, false);
    }



    private void initData() {

    }
}
