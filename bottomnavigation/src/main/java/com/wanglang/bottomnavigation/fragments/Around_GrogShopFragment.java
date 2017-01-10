package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.wanglang.bottomnavigation.entitys.CategrayTagEntity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Around_GrogShopFragment extends AroundBaseFragment {

    public Around_GrogShopFragment(ArrayList<CategrayTagEntity> mArrTags) {
        super(mArrTags,1);
    }

    public Around_GrogShopFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
