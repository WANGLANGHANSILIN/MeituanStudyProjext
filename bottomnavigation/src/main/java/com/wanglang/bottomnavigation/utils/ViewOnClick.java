package com.wanglang.bottomnavigation.utils;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.activitys.AllCateGoryActivity;
import com.wanglang.bottomnavigation.activitys.StudyActivity;
import com.wanglang.bottomnavigation.entitys.TopEntity;

import java.util.ArrayList;

public class ViewOnClick implements View.OnClickListener {
        private int position;
        private ArrayList<TopEntity> mTopEntities;
        private Context mContext;
        public ViewOnClick(Context context,int pos, ArrayList<TopEntity> arrayList){
            this.position = pos;
            this.mTopEntities = arrayList;
            this.mContext = context;
        }

        @Override
        public void onClick(View v) {
            v.setBackgroundResource(R.drawable.ic_draw_item_bg);
            TT.showText(mTopEntities.get(position).getName());
            if (mTopEntities.get(position).getName().equals("更多")){
                mContext.startActivity(new Intent(new Intent(mContext, AllCateGoryActivity.class)));
            }
            if (position == 7){
                mContext.startActivity(new Intent(new Intent(mContext, StudyActivity.class)));
            }
        }
    }