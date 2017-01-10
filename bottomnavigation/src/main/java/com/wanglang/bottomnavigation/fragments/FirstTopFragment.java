package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.adapters.RcBaseAdapter;
import com.wanglang.bottomnavigation.adapters.RcBaseHodler;
import com.wanglang.bottomnavigation.entitys.TopEntity;
import com.wanglang.bottomnavigation.utils.ViewOnClick;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstTopFragment extends Fragment {


    private RecyclerView mRcFirst;
    private int[] mImgIds = new int[]{R.mipmap.ic_category_0,R.mipmap.ic_category_1,R.mipmap.ic_category_2,R.mipmap.ic_category_10,R.mipmap.ic_category_34
            ,R.mipmap.ic_category_25,R.mipmap.ic_category_3,R.mipmap.ic_category_6,R.mipmap.ic_category_11,R.mipmap.ic_category_16};

    private String[] mTexts = new String[]{"美食","电影","酒店住宿","休闲娱乐","外卖","机票/火车票","KTV","周边游","丽人","景点"};
    private ArrayList<TopEntity> mTopEntities;

    public FirstTopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_top, container, false);
        mRcFirst = (RecyclerView) view.findViewById(R.id.rc_frgt_top_first);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mRcFirst == null)
            Log.e("onActivityCreated",""+(mRcFirst == null));
        mRcFirst.setLayoutManager(new GridLayoutManager(getContext(),5));

        mTopEntities = new ArrayList<>();
        for (int i = 0; i < mTexts.length; i++) {
            TopEntity topEntity = new TopEntity(String.valueOf(mImgIds[i]),mTexts[i]);
            mTopEntities.add(topEntity);
        }
        mRcFirst.setAdapter(new RcBaseAdapter<TopEntity>(mTopEntities) {
            @Override
            protected int setLayoutid(int viewType) {
                return R.layout.item_rc_frag_top;
            }

            @Override
            protected void handleViewData(RcBaseHodler holder, int position) {
                holder.setImageResource(R.id.iv_item_fra_top,Integer.valueOf(mTopEntities.get(position).getImgUrl()));
                holder.setText(R.id.tv_item_fra_top,mTopEntities.get(position).getName());
                ViewOnClick viewOnClick = new ViewOnClick(getActivity(),position, mTopEntities);
                holder.setOnClickListener(R.id.ll_item_frag_top, viewOnClick);
            }
        });
    }
}
