package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.entitys.CategrayTagEntity;
import com.wanglang.bottomnavigation.utils.TT;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AroundFragment extends Fragment {


    @BindView(R.id.tv_layout_around_address)
    TextView mTvLayoutAroundAddress;
    @BindView(R.id.et_layout_around_search)
    EditText mEtLayoutAroundSearch;
    @BindView(R.id.tv_layout_around_cate)
    TextView mTvLayoutAroundCate;
    @BindView(R.id.tv_layout_around_grogshop)
    TextView mTvLayoutAroundGrogshop;
    @BindView(R.id.tv_layout_around_libertinism)
    TextView mTvLayoutAroundLibertinism;
    @BindView(R.id.tv_layout_around_all)
    TextView mTvLayoutAroundAll;
    @BindView(R.id.view_layout_around_segmentation_1)
    View mSegmentation1;
    @BindView(R.id.view_layout_around_segmentation_2)
    View mSegmentation2;
    @BindView(R.id.view_layout_around_segmentation_3)
    View mSegmentation3;
    @BindView(R.id.view_layout_around_segmentation_4)
    View mSegmentation4;
    private FragmentManager mChildFragmentManager;

    private String[] mTagCatas = {"热门","面包甜点","小吃快餐","北京菜","川菜","日本料理","韩国料理","江浙菜"};
    private String[] mTagGrogShop = {"热门","商务出行","品牌连锁","公寓民宿","客栈青旅","高星特惠","情侣专享"};
    private String[] mTagLibertinism = {"热门","KTV","足疗按摩","洗浴汗蒸","电影院","美发","美甲","桌游/电玩"};

    private ArrayList<CategrayTagEntity> mTagArrCatas;
    private ArrayList<CategrayTagEntity> mTagArrGrogShop;
    private ArrayList<CategrayTagEntity> mTagArrLibertinism;

    public AroundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_around2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        initData();

        mChildFragmentManager = getChildFragmentManager();
        mChildFragmentManager.beginTransaction().add(R.id.fl_layout_near,new Around_CateFragment(mTagArrCatas),"catefragment").addToBackStack("catefragment").commit();

        mTvLayoutAroundCate.setOnClickListener(new MyOnClickListener());
        mTvLayoutAroundGrogshop.setOnClickListener(new MyOnClickListener());
        mTvLayoutAroundLibertinism.setOnClickListener(new MyOnClickListener());
        mTvLayoutAroundAll.setOnClickListener(new MyOnClickListener());

        mTvLayoutAroundCate.setSelected(true);
        mSegmentation1.setSelected(true);
    }

    private void initData() {
        mTagArrCatas = new ArrayList<>();
        mTagArrGrogShop = new ArrayList<>();
        mTagArrLibertinism = new ArrayList<>();

        for (int i = 0; i < mTagCatas.length; i++) {
            mTagArrCatas.add(new CategrayTagEntity(mTagCatas[i],false));
            mTagArrCatas.get(0).setiSeletor(true);
        }
        for (int i = 0; i < mTagGrogShop.length; i++) {
            mTagArrGrogShop.add(new CategrayTagEntity(mTagGrogShop[i],false));
            mTagArrGrogShop.get(0).setiSeletor(true);
        }
        for (int i = 0; i < mTagLibertinism.length; i++) {
            mTagArrLibertinism.add(new CategrayTagEntity(mTagLibertinism[i],false));
            mTagArrLibertinism.get(0).setiSeletor(true);
        }
    }

    @OnClick(R.id.tv_layout_around_address)
    public void onClick(){
        TT.showText(mTvLayoutAroundAddress.getText().toString());
    }


    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            initSementation();
            FragmentTransaction mFragmentTransaction = mChildFragmentManager.beginTransaction();
            switch (v.getId()){
                case R.id.tv_layout_around_cate:
                    mSegmentation1.setSelected(true);
                    mTvLayoutAroundCate.setSelected(true);
                    mFragmentTransaction.replace(R.id.fl_layout_near,new Around_CateFragment(mTagArrCatas),"catefragment");
                    mFragmentTransaction.addToBackStack("catefragment");// 添加到Activity管理的回退栈中。
                    TT.showText(mTvLayoutAroundCate.getText().toString());
                    break;

                case R.id.tv_layout_around_grogshop:
                    mSegmentation2.setSelected(true);
                    mTvLayoutAroundGrogshop.setSelected(true);
                    mFragmentTransaction.replace(R.id.fl_layout_near,new Around_GrogShopFragment(mTagArrGrogShop),"grogshopragment");
                    mFragmentTransaction.addToBackStack("grogshopragment");
                    TT.showText(mTvLayoutAroundGrogshop.getText().toString());
                    break;

                case R.id.tv_layout_around_libertinism:
                    mSegmentation3.setSelected(true);
                    mTvLayoutAroundLibertinism.setSelected(true);
                    mFragmentTransaction.replace(R.id.fl_layout_near,new Around_LibertinismFragment(mTagArrLibertinism),"libertinismfragment");
                    mFragmentTransaction.addToBackStack("libertinismfragment");
                    TT.showText(mTvLayoutAroundLibertinism.getText().toString());
                    break;

                case R.id.tv_layout_around_all:
                    mSegmentation4.setSelected(true);
                    mTvLayoutAroundAll.setSelected(true);
                    mFragmentTransaction.replace(R.id.fl_layout_near,new Around_AllFragment(),"allfragment");
                    mFragmentTransaction.addToBackStack("allfragment");

                    TT.showText(mTvLayoutAroundAll.getText().toString());
                    break;
            }
            mFragmentTransaction.commit();
        }

        private void initSementation() {
            mSegmentation1.setSelected(false);
            mSegmentation2.setSelected(false);
            mSegmentation3.setSelected(false);
            mSegmentation4.setSelected(false);



            mTvLayoutAroundCate.setSelected(false);
            mTvLayoutAroundGrogshop.setSelected(false);
            mTvLayoutAroundLibertinism.setSelected(false);
            mTvLayoutAroundAll.setSelected(false);
        }
    }


}
