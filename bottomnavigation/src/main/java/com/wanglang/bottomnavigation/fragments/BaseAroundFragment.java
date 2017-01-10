package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.utils.TT;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseAroundFragment extends Fragment {

    @BindView(R.id.tv_layout_near_tag_1)
    TextView mTvLayoutNearTag1;
    @BindView(R.id.tv_layout_near_tag_2)
    TextView mTvLayoutNearTag2;
    @BindView(R.id.tv_layout_near_tag_3)
    TextView mTvLayoutNearTag3;
    @BindView(R.id.tv_layout_near_tag_4)
    TextView mTvLayoutNearTag4;
    @BindView(R.id.tv_layout_near_tag_5)
    TextView mTvLayoutNearTag5;
    @BindView(R.id.tv_layout_near_tag_6)
    TextView mTvLayoutNearTag6;
    @BindView(R.id.tv_layout_near_tag_7)
    TextView mTvLayoutNearTag7;
    @BindView(R.id.tv_layout_near_tag_8)
    TextView mTvLayoutNearTag8;
    @BindView(R.id.rc_frag_cate)
    RecyclerView mRcFragCate;
    private static ArrayList<TextView> mTextViews;
    public int mTagKey;
    private View mIncludeView;
    private static HashMap<String,Integer> mHashMap = new HashMap<>();
    private static String mTag;
    public static SparseBooleanArray mSparseBooleanArray = new SparseBooleanArray();
    public BaseAroundFragment() {
//        this.mTagKey = tagKey;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cate, container, false);
        mIncludeView = view.findViewById(R.id.include_fragment_cate);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTag = getTag();
        initTagText();
        switchTag();
        for (TextView textView : mTextViews) {
            textView.setOnClickListener(new ViewOnClickListener());
        }
        int viewId = -1;
        Log.e("initSele","  "+mSparseBooleanArray.size()+"   "+mSparseBooleanArray.indexOfValue(true));
        if (mSparseBooleanArray.indexOfValue(true) < 0){
            viewId = mTextViews.get(0).getId();
        }else
            viewId = mSparseBooleanArray.keyAt(mSparseBooleanArray.indexOfValue(true));
        Integer integer = mHashMap.get(mTag);
        if (integer != null && integer != viewId){
            viewId = integer;
        }
        switchSeletor(viewId);
    }

    private void switchTag() {
        if (mTag.equals("catefragment")){
            mIncludeView.setVisibility(View.VISIBLE);
        }else if (mTag.equals("grogshopragment")){
            mIncludeView.setVisibility(View.VISIBLE);
        }else if (mTag.equals("libertinismfragment")){
            mIncludeView.setVisibility(View.VISIBLE);
            mTvLayoutNearTag8.setVisibility(View.INVISIBLE);
        }else if (mTag.equals("allfragment")){
            mIncludeView.setVisibility(View.GONE);
        }
    }

    private void setTextViewTag(int id,String tag){
        for (TextView mTextView : mTextViews) {
            mTextView.setTag(id,tag);
        }
    }


    private static void switchSeletor(int id) {
        initSele(id);
        TextView textView = mTextViews.get(getTextViewIndex(id));
        mHashMap.put(mTag,id);
        switch (id){
            case R.id.tv_layout_near_tag_1:
                break;
            case R.id.tv_layout_near_tag_2:
                break;
            case R.id.tv_layout_near_tag_3:
                break;
            case R.id.tv_layout_near_tag_4:
                break;
            case R.id.tv_layout_near_tag_5:
                break;
            case R.id.tv_layout_near_tag_6:
                break;
            case R.id.tv_layout_near_tag_7:
                break;
            case R.id.tv_layout_near_tag_8:
                break;
        }
    }


    private static void initSele(int id) {

        for (TextView view : mTextViews) {
            mSparseBooleanArray.put(view.getId(),false);
            view.setSelected(false);
            if (id == view.getId()){
                view.setSelected(true);
                mSparseBooleanArray.put(id,true);
                Log.i("initSele","mTextViews:"+mTextViews.size()+"   "+view.isSelected());
            }
        }

    }

    private void initTagText() {
        mTextViews = new ArrayList<>();
        mTextViews.add(mTvLayoutNearTag1);
        mTextViews.add(mTvLayoutNearTag2);
        mTextViews.add(mTvLayoutNearTag3);
        mTextViews.add(mTvLayoutNearTag4);
        mTextViews.add(mTvLayoutNearTag5);
        mTextViews.add(mTvLayoutNearTag6);
        mTextViews.add(mTvLayoutNearTag7);
        mTextViews.add(mTvLayoutNearTag8);
    }

    private static TextView getView(int id){
        for (TextView mTextView : mTextViews) {
            if (mTextView.getId() == id){
                return mTextView;
            }
        }
        return null;
    }

    public static int getTextViewIndex(int id){
        TextView textView = getView(id);
        for (int i = 0; i < mTextViews.size(); i++) {
            if (textView.getId() == mTextViews.get(i).getId()){
                return i;
            }
        }
        return -1;
    }

    private class ViewOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            TT.showText(((TextView)v).getText().toString());
//            v.setBackgroundResource(R.drawable.near_tag_seletor_bg);
//            initSele();
//            mSparseBooleanArray.put(v.getId(),true);
//            v.setSelected(mSparseBooleanArray.get);
            switchSeletor(v.getId());
        }
    }
}
