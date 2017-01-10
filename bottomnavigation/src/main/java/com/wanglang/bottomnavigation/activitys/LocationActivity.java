package com.wanglang.bottomnavigation.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.adapters.LocationAdapter;
import com.wanglang.bottomnavigation.adapters.RcBaseAdapter;
import com.wanglang.bottomnavigation.adapters.RcBaseHodler;
import com.wanglang.bottomnavigation.entitys.CityEntity;
import com.wanglang.bottomnavigation.utils.HanziToPinyin;
import com.wanglang.bottomnavigation.utils.ParseUtils;
import com.wanglang.bottomnavigation.utils.SpUtils;
import com.wanglang.bottomnavigation.view.MyItemDecoration;
import com.wanglang.bottomnavigation.view.SortView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.rc_layout_location)
    RecyclerView mRcLayoutLocation;
    @BindView(R.id.st_layout_location)
    SortView mStLayoutLocation;
    @BindView(R.id.tv_layout_location_letter)
    TextView mTvLayoutLocationLetter;
    @BindView(R.id.bt_item_title_ocation_retrun)
    Button mBtTitleRetrun;
    @BindView(R.id.tv_item_title_ocation_inland)
    TextView mTvTitleInland;
    @BindView(R.id.tv_item_title_ocation_overseas)
    TextView mTvTitleOverseas;
    @BindView(R.id.ed_layout_location_search_city)
    EditText mEdSearchCity;
    @BindView(R.id.activity_location)
    LinearLayout mActivityLocation;


    private ArrayList<CityEntity> mCityEntities;
    private LocationAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<CityEntity.CityListShowEntity> mCityListShowEntities;
    private ArrayList<CityEntity> mCityEntities1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);


        getCityInfo();

        getCityList();

        initRecycleView();
        mTvTitleInland.setSelected(true);

        mStLayoutLocation.setListener(new SortView.OnScrollMoveListener() {
            @Override
            public void onMove(String s) {
                if (s.equals("")) {
                    mTvLayoutLocationLetter.setVisibility(View.GONE);
                } else {
                    mTvLayoutLocationLetter.setVisibility(View.VISIBLE);
                }
                mTvLayoutLocationLetter.setText(s);
//                mRcLayoutLocation.scrollToPosition();
                mLinearLayoutManager.scrollToPositionWithOffset(getPostion(s), 0);
            }
        });

        mRcLayoutLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑动的时候显示字母，未滑动的时候需要隐藏
                if (newState == 1)
                    mTvLayoutLocationLetter.setVisibility(View.VISIBLE);
                else {
                    mTvLayoutLocationLetter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取当前屏幕显示的第一个item的position
                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                String s = mCityListShowEntities.get(firstVisibleItemPosition).getLetter();
                if (s.equals("")) {
                    mTvLayoutLocationLetter.setVisibility(View.GONE);
                } else {
                    mTvLayoutLocationLetter.setVisibility(View.VISIBLE);
                }
                mTvLayoutLocationLetter.setText(getLetter(s) + "");
            }
        });

        mEdSearchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String city = s.toString();
                Log.i("afterTextChanged",""+city);
                mCityEntities1 = new ArrayList<CityEntity>();

                for (CityEntity cityEntity : mCityEntities) {
                    if (cityEntity.getName().contains(city) || cityEntity.getPinyin().contains(city) || cityEntity.getAcronym().contains(city)){
                        mCityEntities1.add(cityEntity);
                    }
                }
                mRcLayoutLocation.setVisibility(View.VISIBLE);
                mStLayoutLocation.setVisibility(View.GONE);
                if (mCityEntities1.size() == 0){
                    mCityEntities1.add(new CityEntity("无该城市的信息"));
                }

                mRcLayoutLocation.setAdapter(new RcBaseAdapter(mCityEntities1) {
                    @Override
                    protected int setLayoutid(int viewType) {
                        return android.R.layout.simple_list_item_1;
                    }

                    @Override
                    protected void handleViewData(RcBaseHodler holder, int position) {
                        TextView view = holder.getView(android.R.id.text1);
                        view.setText(mCityEntities1.get(position).getName());
                        view.setBackgroundColor(getResources().getColor(R.color.write));
                        view.setTextColor(Color.BLUE);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mEdSearchCity.setText(((TextView)v).getText().toString());
                                mRcLayoutLocation.setVisibility(View.GONE);
                            }
                        });
                    }
                });

            }
        });

    }

    private void initRecycleView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRcLayoutLocation.setLayoutManager(mLinearLayoutManager);


        ArrayList<String> haha = new ArrayList<>();
        for (CityEntity.CityListShowEntity entity : mCityListShowEntities) {
            haha.add(entity.getLetter());
        }
        mStLayoutLocation.setLetters(haha);

        mRcLayoutLocation.addItemDecoration(new MyItemDecoration(this, LinearLayoutManager.VERTICAL, R.drawable.ic_drawable_divider));
        mAdapter = new LocationAdapter(mCityListShowEntities);
        mRcLayoutLocation.setAdapter(mAdapter);
    }

    private void getCityList() {
        ArrayList<String> strings = new ArrayList<>();
        for (CityEntity cityEntity : mCityEntities) {
            char c = cityEntity.getAcronym().toUpperCase().charAt(0);
            strings.add(c + "");
        }

        mCityListShowEntities = new ArrayList<>();

        HashMap<String, ArrayList<String>> listHashMap = new HashMap<>();
        for (String string : strings) {
            ArrayList<String> citys = new ArrayList<>();
            for (CityEntity cityEntity : mCityEntities) {
                char c = cityEntity.getAcronym().toUpperCase().charAt(0);
                if (string.charAt(0) == c) {
                    citys.add(cityEntity.getName());
                }
            }
            listHashMap.put(string, citys);
        }


//        mCityListShowEntities.add(0,new CityEntity.CityListShowEntity("县区"));
//        mCityListShowEntities.add(1,new CityEntity.CityListShowEntity("定位"));
//        mCityListShowEntities.add(2,new CityEntity.CityListShowEntity("最近"));


        CityEntity.CityListShowEntity top = new CityEntity.CityListShowEntity("热门");
        ArrayList<String> top4 = new ArrayList<>();
        for (CityEntity cityEntity : mCityEntities) {
            String rank = cityEntity.getRank();
            if (rank.length() > 0 && (rank.charAt(0) == 'A' || rank.charAt(0) == 'S')) {
                top4.add(cityEntity.getName());
            }
        }
        top.setCityList(HanziToPinyin.toFistLeter(top4));
        mCityListShowEntities.add(top);

        CityEntity.CityListShowEntity top3 = new CityEntity.CityListShowEntity("最近");
        ArrayList<String> strings1 = new ArrayList<>();
        Set<String> message = SpUtils.getMessage(this, "Location", "Lt");
        if (message != null) {
            for (String s : message) {
                strings1.add(s);
            }
            top3.setCityList(HanziToPinyin.toFistLeter(strings1));
            mCityListShowEntities.add(0, top3);
//            HanziToPinyin.getInstance().get
        }

        CityEntity.CityListShowEntity top1 = new CityEntity.CityListShowEntity("县区");
        ArrayList<String> strings3 = new ArrayList<>();
        strings3.add(getIntent().getStringExtra("CurentCity"));
        top1.setCityList(strings3);

        mCityListShowEntities.add(0, top1);

        CityEntity.CityListShowEntity top2 = new CityEntity.CityListShowEntity("定位");
        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("北京");
        top2.setCityList(strings2);
        mCityListShowEntities.add(1, top2);


        SortedMap sortedMap = new TreeMap(listHashMap);
        for (int i = 0; i < sortedMap.size(); i++) {
            String string = (String) sortedMap.keySet().toArray()[i];
            CityEntity.CityListShowEntity cityListShowEntity = new CityEntity.CityListShowEntity(string);
            ArrayList<String> stringArrayList = (ArrayList<String>) sortedMap.get(string);
            Log.i("getCityList", "string:" + string + "   " + stringArrayList.size());

            cityListShowEntity.setCityList(stringArrayList);
            mCityListShowEntities.add(cityListShowEntity);
        }
    }

    private CityEntity.CityListShowEntity getCityListShowEntity(String string) {
        for (CityEntity.CityListShowEntity cityListShowEntity : mCityListShowEntities) {
            if (string.equals(cityListShowEntity.getLetter())) {
                return cityListShowEntity;
            }
        }
        return new CityEntity.CityListShowEntity(string);
    }

    private void getCityInfo() {
        String jsonData = ParseUtils.parseData(this, "cities.json");
        JSONObject jsonObject = JSON.parseObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        mCityEntities = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(jsonObject1.getString("name"));
            cityEntity.setRank(jsonObject1.getString("rank"));
            cityEntity.setAcronym(jsonObject1.getString("acronym"));
            cityEntity.setPinyin(jsonObject1.getString("pinyin"));
            mCityEntities.add(cityEntity);
        }
    }

    private int getPostion(String s) {
        for (int i = 0; i < mCityListShowEntities.size(); i++) {
            if (s.equals(mCityListShowEntities.get(i).getLetter())) {
                return i;
            }
        }
        return -1;
    }

    private String getLetter(String s) {
        for (CityEntity cityEntity : mCityEntities) {
            if (s.equals(cityEntity.getName())) {
                return cityEntity.getAcronym().toUpperCase().charAt(0) + "";
            }
        }
//        return s.toUpperCase().charAt(0);
        return s;
    }

    public void initSeletorTitle(){
        mTvTitleInland.setSelected(false);
        mTvTitleOverseas.setSelected(false);
    }

   @OnClick(R.id.tv_item_title_ocation_inland)
    public void OnClick3(View v){
       initSeletorTitle();
       mTvTitleInland.setSelected(true);
    }

    @OnClick(R.id.tv_item_title_ocation_overseas)
    public void OnClick2(View v){
        initSeletorTitle();
        mTvTitleOverseas.setSelected(true);
    }

    @OnClick(R.id.bt_item_title_ocation_retrun)
    public void OnClick1(View v){
        this.finish();
    }

}
