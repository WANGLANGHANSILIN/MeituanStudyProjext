package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class SrcondFragment extends Fragment{


    private RecyclerView mRcSecond;

    private int[] mImgIds = new int[]{R.mipmap.ic_category_38,R.mipmap.ic_category_12,R.mipmap.ic_category_29,R.mipmap.ic_category_37,R.mipmap.ic_category_28
            ,R.mipmap.ic_category_13,R.mipmap.ic_category_6,R.mipmap.ic_category_22,R.mipmap.ic_category_27,R.mipmap.ic_category_15};

    private String[] mTexts = new String[]{"品质酒店","足疗按摩","美发","母婴亲子","结婚","主题公园","水上乐园","学习培训","家装","更多"};
    private ArrayList<TopEntity> mTopEntities;


    public SrcondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_top, container, false);
        mRcSecond = (RecyclerView) view.findViewById(R.id.rc_frgt_top_first);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRcSecond.setLayoutManager(new GridLayoutManager(getActivity(),5));

        mTopEntities = new ArrayList<>();
        for (int i = 0; i < mTexts.length; i++) {
            TopEntity topEntity = new TopEntity(String.valueOf(mImgIds[i]),mTexts[i]);
            mTopEntities.add(topEntity);
        }

        mRcSecond.setAdapter(new RcBaseAdapter<TopEntity>(mTopEntities) {
            @Override
            protected int setLayoutid(int viewType) {
                return R.layout.item_rc_frag_top;
            }

            @Override
            protected void handleViewData(RcBaseHodler holder, int position) {
                holder.setImageResource(R.id.iv_item_fra_top, Integer.valueOf(mTopEntities.get(position).getImgUrl()));
                holder.setText(R.id.tv_item_fra_top, mTopEntities.get(position).getName());

                holder.setOnClickListener(R.id.ll_item_frag_top,new ViewOnClick(getActivity(),position,mTopEntities));
            }
        });

    }
}
