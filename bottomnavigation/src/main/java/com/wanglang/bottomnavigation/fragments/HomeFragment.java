package com.wanglang.bottomnavigation.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.activitys.LocationActivity;
import com.wanglang.bottomnavigation.adapters.MyFragmentPagerAdapter;
import com.wanglang.bottomnavigation.adapters.MyPagerAdapter;
import com.wanglang.bottomnavigation.adapters.RcNoticeAdapter;
import com.wanglang.bottomnavigation.dialog.ScanDialog;
import com.wanglang.bottomnavigation.entitys.NoticeEntity;
import com.wanglang.bottomnavigation.entitys.RecomenderEntity;
import com.wanglang.bottomnavigation.entitys.TopEntity;
import com.wanglang.bottomnavigation.utils.ParseUtils;
import com.wanglang.bottomnavigation.utils.SpUtils;
import com.wanglang.bottomnavigation.utils.TT;
import com.wanglang.bottomnavigation.view.MyIndicator;
import com.wanglang.bottomnavigation.view.MyItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.tv_layout_home_address)
    TextView mTvLayoutHomeAddress;
    @BindView(R.id.et_layout_home_search)
    EditText mEtLayoutHomeSearch;
    @BindView(R.id.iv_layout_home_scan)
    ImageView mIvLayoutHomeScan;
    @BindView(R.id.iv_layout_home_msg)
    ImageView mIvLayoutHomeMsg;



    private ViewPager mAdVPage;

    private Handler mHandler;
    private MyPagerAdapter mPagerAdapter;
    private RecyclerView mRcList;
    private ArrayList<RecomenderEntity> mRecomenderEntities;
    private ArrayList<NoticeEntity> mNoticeEntities;
    private ArrayList<Fragment> mFragments;




    private String[] mReTitles = new String[]{"抽200红包", "天天满减", "0元霸王餐", "航海王", "1元抢特价", "晒新家"};
    private String[] mPreTitle = new String[]{"双11美食特惠", "每天有新优惠", "会员免单福利", "19.9元起", "旅游双11来啦", "领10元红包"};
    private int[] mDraId = new int[]{R.mipmap.icon_home_hotel_302, R.mipmap.icon_home_movie_29, R.mipmap.icon_home_999, R.mipmap.icon_home_all_0, R.mipmap.icon_home_8888, R.mipmap.icon_home_food_99};
    private int[] mIcons = new int[]{
            R.mipmap.ic_category_0, R.mipmap.ic_category_1, R.mipmap.ic_category_2, R.mipmap.ic_category_3
            , R.mipmap.ic_category_4, R.mipmap.ic_category_5, R.mipmap.ic_category_6, R.mipmap.ic_category_7, R.mipmap.ic_category_8, R.mipmap.ic_category_9, R.mipmap.ic_category_10
            , R.mipmap.ic_category_11, R.mipmap.ic_category_12, R.mipmap.ic_category_13, R.mipmap.ic_category_14, R.mipmap.ic_category_15, R.mipmap.ic_category_16, R.mipmap.ic_category_22
            , R.mipmap.ic_category_23, R.mipmap.ic_category_24, R.mipmap.ic_category_25, R.mipmap.ic_category_26, R.mipmap.ic_category_27, R.mipmap.ic_category_28, R.mipmap.ic_category_29
            , R.mipmap.ic_category_30, R.mipmap.ic_category_31, R.mipmap.ic_category_32, R.mipmap.ic_category_33, R.mipmap.ic_category_34, R.mipmap.ic_category_35, R.mipmap.ic_category_36
            , R.mipmap.ic_category_37, R.mipmap.ic_category_38};
    private String mNoticeName[] = new String[]{"华莱士", "正新鸡排", "黄茶宇治抹茶", "真功夫", "皇冠~奥尔玛", "一元夺IPhone7", "御可贡茶", "火呗开心花甲"};
    private String mNoticeDec[] = new String[]{"[多城市]经典单人餐，提供免单WiFi", "[全国]【官方】正新鸡排尊享版单人餐", "[时尚天河]鸡蛋仔1份", "[多城市]冬香菇鸡腿肉饭+卤蛋1份"
            , "[98店通用]20元面包代金劵，节假日可用", "[全国]超高中奖率，不信来试", "[同德围]饮品8选1，提供免费WiFi", "[3店通用]花甲餐3选1，提供免费WiFi"};

    private String mNoticePrice[] = new String[]{"¥10.9 门市价:¥21", "¥9.9 门市价:¥20", "¥1 门市价:¥10", "¥16 门市价:¥18.5", "¥10.9 门市价:¥21"
            , "¥15.8 新客减20", "¥1 门市价:¥1", "¥11.9 门市价:¥19", "¥15.9 门市价:¥20"};
    private MyIndicator mIndicator;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mAdVPage = (ViewPager) view.findViewById(R.id.vp_home);
        mIndicator = (MyIndicator) view.findViewById(R.id.home_indicator);
        mRcList = (RecyclerView) view.findViewById(R.id.rc_list);
        ButterKnife.bind(this, view);
        return view;
    }

    private static boolean stopAutoScroll = false;//是否自动滚动

    private Runnable timerRun = new Runnable() { //没4s自动滚动页面
        @Override
        public void run() {
            if (!stopAutoScroll) {
                mAdVPage.setCurrentItem(mAdVPage.getCurrentItem() + 1, true);
                mHandler.postDelayed(this, 4000);
            }
        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int[] imageIds = new int[]{R.mipmap.icon_home_hotel_302, R.mipmap.icon_home_ktv_31, R.mipmap.icon_home_life_46,
                R.mipmap.icon_home_movie_29};
        Log.e("HomeFragment", "onActivityCreated" + stopAutoScroll);
        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            ViewPager.LayoutParams params = new ViewPager.LayoutParams();
            if (params != null) {
                params.width = ViewPager.LayoutParams.MATCH_PARENT;
                params.height = ViewPager.LayoutParams.MATCH_PARENT;
                imageView.setLayoutParams(params);
            } else {

                Log.e("HomeFragment", "params is null");
            }
            imageView.setImageResource(imageIds[i]);
            views.add(imageView);
        }
        mHandler = new Handler();

//        initFragmentData();
//        mPagerAdapter = new MyPagerAdapter(views);

        mAdVPage.setCurrentItem(Integer.MAX_VALUE / 2 / views.size());
        mAdVPage.setCurrentItem(0);

        initRecyclerView();
    }

    /**
     * 加载fragment的数据
     */
    private void initFragmentData() {

        String parseData = ParseUtils.parseData(getActivity(), "takeout_category.json");
        JSONArray jsonArray = JSON.parseObject(parseData).getJSONObject("data").getJSONArray("primary_filter");
        ArrayList<TopEntity> topEntities = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            topEntities.add(new TopEntity(jsonObject.getString("url"),jsonObject.getString("name")));
        }
    }

    /**
     * 初始化RecyclerView，包括数据加载，事件监听
     */
    private void initRecyclerView() {
        initRecyclerData();

        mAdVPage.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),mFragments));
        mIndicator.setCount(mFragments.size());
        mIndicator.setViewPager(mAdVPage);



        mRcList.setLayoutManager(new LinearLayoutManager(getActivity()));
        RcNoticeAdapter noticeAdapter = new RcNoticeAdapter(mNoticeEntities,mRecomenderEntities);
        mRcList.addItemDecoration(new MyItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.ic_drawable_divider1));
        mRcList.setAdapter(noticeAdapter);
        Log.i("initRecyclerView", mNoticeEntities.size() + "");
    }



    /**
     * 初始化RecyclerView所加载的数据，一般都是从网络加载的
     */
    private void initRecyclerData() {

        mFragments = new ArrayList<>();
        mFragments.add(new FirstTopFragment());
        mFragments.add(new SrcondFragment());


        mRecomenderEntities = new ArrayList<>();
        for (int i = 0; i < mReTitles.length; i++) {
            RecomenderEntity entity = new RecomenderEntity();
            entity.setReTitle(mReTitles[i]);
            entity.setRePreTitle(mPreTitle[i]);
            entity.setReid(mDraId[i]);
            mRecomenderEntities.add(entity);
        }



        mNoticeEntities = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int v = (int) (Math.random() * 34);
            int l = (int) (Math.random() * mNoticeDec.length);
            NoticeEntity entity = new NoticeEntity(mIcons[v], mNoticeName[l], mNoticeDec[l], mNoticePrice[l], (int) (Math.random() * 100), (int) (Math.random() * 10000));
            mNoticeEntities.add(entity);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("HomeFragment", "onStart");
        mTvLayoutHomeAddress.setText((SpUtils.sStrings1 != null && SpUtils.sStrings1.size() > 0)?SpUtils.sStrings1.get(0):mTvLayoutHomeAddress.getText().toString());
    }

    //点击跳转到城市地址选项
    @OnClick(R.id.tv_layout_home_address)
    public void OnClick(){
        TT.showText(mTvLayoutHomeAddress.getText().toString());
        Intent intent = new Intent(getActivity(), LocationActivity.class);
        intent.putExtra("CurentCity",mTvLayoutHomeAddress.getText().toString());
        getActivity().startActivityForResult(intent,1);
    }

    //从城市界面返回来的数据
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){
            Log.i("onActivityResult","requestCode:"+requestCode+"   resultCode:"+resultCode+"   "+data.getStringExtra("city"));
            String cities = data.getStringExtra("city");
            mTvLayoutHomeAddress.setText(cities != null?cities:mTvLayoutHomeAddress.getText().toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("HomeFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("HomeFragment", "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("HomeFragment", "onDestroyView");
        stopAutoScroll = false;
        mHandler.removeCallbacks(timerRun);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("HomeFragment", "onDestroy");
    }

    //扫一扫和付款碼
    @OnClick(R.id.iv_layout_home_scan)
    public void onClickScan(View view){
        ScanDialog scanDialog = new ScanDialog();
        scanDialog.show(getFragmentManager(),"ScanDialog");
    }


    //消息提醒显示按钮
    @OnClick(R.id.iv_layout_home_msg)
    public void onClickMsg(View view){

    }
}
