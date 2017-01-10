package com.wanglang.bottomnavigation.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.activitys.AboutMeituanActivity;
import com.wanglang.bottomnavigation.activitys.BalanceActivity;
import com.wanglang.bottomnavigation.activitys.CallCenterActivity;
import com.wanglang.bottomnavigation.activitys.ClubCardActivity;
import com.wanglang.bottomnavigation.activitys.IntegralMallActivity;
import com.wanglang.bottomnavigation.activitys.LoginRegisterActivity;
import com.wanglang.bottomnavigation.activitys.MemberCenterActivity;
import com.wanglang.bottomnavigation.activitys.MyOrderFormActivity;
import com.wanglang.bottomnavigation.activitys.MyRatingActivity;
import com.wanglang.bottomnavigation.activitys.MyWalletActivity;
import com.wanglang.bottomnavigation.activitys.VoucherActivity;
import com.wanglang.bottomnavigation.activitys.WhereFriendsActivity;
import com.wanglang.bottomnavigation.adapters.MineListAdapter;
import com.wanglang.bottomnavigation.entitys.PersonInfoEntity;
import com.wanglang.bottomnavigation.utils.TT;
import com.wanglang.bottomnavigation.view.MyItemDecoration;

import java.util.ArrayList;

/**
     * A simple {@link Fragment} subclass.
     */
    public class MineFragment extends Fragment {


    private RecyclerView mRcSetting;

    private String[] mStrings = new String[]{"我的订单","我的钱包","余额","抵用券","会员卡","好友去哪","我的评价","会员中心","积分商城","客服中心","关于美团"};
    private int[] mImgIds = {R.mipmap.order_all_order,R.mipmap.hui_icon_benefit,R.mipmap.homepage_my_balance,R.mipmap.ic_global_user_membercard,R.mipmap.ic_global_user_voucher,
            R.mipmap.ic_global_usercenter,R.mipmap.ic_global_vip_club,R.mipmap.ic_my_friends,R.mipmap.ic_my_comment,
            R.mipmap.ic_global_user_feedback,R.mipmap.ic_global_user_aboutmt};
    private ArrayList<PersonInfoEntity> mInfoEntities;

    private Class[] mClasses = {MyOrderFormActivity.class,MyWalletActivity.class, BalanceActivity.class, VoucherActivity.class, ClubCardActivity.class,
            WhereFriendsActivity.class, MyRatingActivity.class, MemberCenterActivity.class, IntegralMallActivity.class, CallCenterActivity.class, AboutMeituanActivity.class};
    private MineListAdapter mMineListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isRefresh = false;

    public MineFragment() {

    }
    
    
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_mine,container,false);
            initView(view);
            return view;
        }

    private void initView(View convertView) {
        mRcSetting = (RecyclerView) convertView.findViewById(R.id.rc_layout_mine);
        mRcSetting.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcSetting.addItemDecoration(new MyItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mRcSetting.setItemAnimator(new DefaultItemAnimator());

        mSwipeRefreshLayout = (SwipeRefreshLayout) convertView.findViewById(R.id.srl_layout_mine);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        mMineListAdapter = new MineListAdapter(mInfoEntities, getActivity());
        mRcSetting.setAdapter(mMineListAdapter);
        mMineListAdapter.setOnItemCickListener(new MineListAdapter.OnItemCickListener() {
            @Override
            public void onClick(View v, int pos, PersonInfoEntity personInfoEntity) {
                try {
                    Log.e("handleViewData","PersonInfoEntity:23  "+personInfoEntity.getPiName()+"   pos:"+pos);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                if (v.getId() == R.id.rl_item_mine){
                    TT.showText(personInfoEntity.getPiName()+"---");
                    Intent intent = new Intent();
                    intent.setClassName(getActivity(),personInfoEntity.getPiPackageName());
                    getActivity().startActivity(intent);
                }else{
                    if (v instanceof TextView){
                        TT.showText(((TextView) v).getText().toString());
                        if (v.getId() == R.id.tv_item_mine_login){
                            getActivity().startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                        }
                    }else{
                        getActivity().startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                    }
                }
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.GREEN,Color.YELLOW,Color.RED);
        mSwipeRefreshLayout.setDistanceToTriggerSync(500);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);

        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//检查是否处于刷新状态
                if (!isRefresh) {
                    isRefresh = true;
                    //模拟加载网络数据，这里设置4秒，正好能看到4色进度条
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            //显示或隐藏刷新进度条
                            mSwipeRefreshLayout.setRefreshing(false);
                            //修改adapter的数据
//                            data.add("这是新添加的数据");
//                            mAdapter.notifyDataSetChanged();
                            isRefresh = false;
                        }
                    }, 2000);
                }
            }
        });


    }

    private void initData() {
        mInfoEntities = new ArrayList<>();
        for (int i = 0; i < mStrings.length; i++) {
            PersonInfoEntity entity = new PersonInfoEntity();
            entity.setPiName(mStrings[i]);
            entity.setPimgId(mImgIds[i]);
            entity.setPiPackageName(mClasses[i].getName());
            Log.i("MineFragment","initData:---"+mClasses[i].getName());
            if (i == 0){
                entity.setPiTextDec("全部订单");
            }else if (i == 7){
                entity.setPiTextDec("好礼已上线");
            }else if(i == 9){
                entity.setPiTextDec("我要合作");
            }else {
                entity.setPiTextDec("");
            }
            mInfoEntities.add(entity);
        }
    }
}