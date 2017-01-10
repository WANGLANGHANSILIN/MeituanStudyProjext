package com.wanglang.bottomnavigation.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.adapters.AllCateGoryAdapter;
import com.wanglang.bottomnavigation.adapters.RcBaseAdapter;
import com.wanglang.bottomnavigation.entitys.CategoryEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCateGoryActivity extends AppCompatActivity {

    @BindView(R.id.rc_layout_allcategory)
    RecyclerView mRcLayoutAllcategory;

    private String[] mTitls ={"热门","美食","电影演出赛事","酒店住宿","周边游","休闲娱乐","生活服务"};
    private ArrayList<CategoryEntity> mCategoryEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cate_gory);
        ButterKnife.bind(this);

        initRecycleView();
    }

    private void initRecycleView() {

        initData();
        mRcLayoutAllcategory.setLayoutManager(new LinearLayoutManager(this));
        RcBaseAdapter rcBaseAdapter = new AllCateGoryAdapter(mCategoryEntities);
        mRcLayoutAllcategory.setAdapter(rcBaseAdapter);
    }

    private void initData() {
        mCategoryEntities = new ArrayList<>();
        String parseJSON = parseJSON();
        Log.i("initData",""+parseJSON);

        JSONObject jsonObject = (JSONObject) JSONObject.parse(parseJSON);
        Log.e("initData",jsonObject.getString("stid"));
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray morepage = data.getJSONArray("morepage");
        if (morepage == null) {
            Log.e("initData"," morepage == null  "+(morepage == null));
            return;
        }
        for (int i = 0; i < morepage.size(); i++) {
            JSONObject jsonObject1 = morepage.getJSONObject(i);
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(jsonObject1.getString("name"));
            categoryEntity.setShowType(jsonObject1.getString("showType"));
            categoryEntity.setId(jsonObject1.getInteger("id"));
            Log.e("initData",categoryEntity.getName());
            if (categoryEntity.getId() == -1 && categoryEntity.getShowType().equals("normal")){
                mCategoryEntities.add(categoryEntity);
            }else {
                categoryEntity.setIconUrl(jsonObject1.getString("iconUrl"));
                JSONArray list = jsonObject1.getJSONArray("list");
                if (list != null) {
                    ArrayList<CategoryEntity.ListBean> listBeen = new ArrayList<>();
                    for (int i1 = 0; i1 < list.size(); i1++) {
                        CategoryEntity.ListBean listBean = new CategoryEntity.ListBean();
                        JSONObject jsonObject2 = list.getJSONObject(i1);
                        listBean.setName(jsonObject2.getString("name"));
                        listBean.setShowType(jsonObject2.getString("showType"));
                        listBean.setType(jsonObject2.getString("type"));
                        listBeen.add(listBean);
                    }
                    categoryEntity.setList(listBeen);
                    mCategoryEntities.add(categoryEntity);
                }
                Log.e("initData","size:"+mCategoryEntities.size());
            }
        }

        Log.e("initData","---"+mCategoryEntities.size());
    }


    public String parseJSON(){
        try {
            InputStream in = getResources().getAssets().open("category.json");
            int available = in.available();
            Log.e("initData","length:"+available);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            Log.e("parseJSON","mStrings:"+builder.toString());
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
