package com.wanglang.bottomnavigation.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.activitys.LocationActivity;
import com.wanglang.bottomnavigation.entitys.CityEntity;
import com.wanglang.bottomnavigation.utils.SpUtils;
import com.wanglang.bottomnavigation.utils.TT;
import com.wanglang.bottomnavigation.view.MyItemDecoration;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/12/13 0013.
 */

public class LocationAdapter extends RcBaseAdapter<CityEntity.CityListShowEntity> {

    private ArrayList<CityEntity.CityListShowEntity> mStrings;

    public LocationAdapter(ArrayList<CityEntity.CityListShowEntity> strings) {
        super(strings);
        this.mStrings = strings;
    }

    @Override
    protected int setLayoutid(int viewType) {
        if (viewType == 0)
            return R.layout.item_rc_location;
        else
            return R.layout.item_rc_location_2;
    }

    @Override
    public int getItemViewType(int position) {
//        if (position >0)
//            return 0;
//        else
            return 0;
    }

    @Override
    protected void handleViewData(RcBaseHodler holder, int position) {
        switch (getItemViewType(position)){
            case 0:
                handleCitySeletor(holder, position);
                break;
            case 1:

                break;
        }
  }

    /**
     * 处理当前城市定位信息
     * @param holder
     */
    private void handleCurentCityLocation(RcBaseHodler holder) {
        TextView view = holder.getView(R.id.tv_item_rc_location_city);
        view.setText("");
        holder.setOnClickListener(R.id.tv_item_rc_location_xianqu, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 处理当前城市选择定位
     * @param holder
     * @param position
     */
    private void handleCitySeletor(RcBaseHodler holder, int position) {
        TextView textView = holder.getView(R.id.tv_item_rc_location);
        RecyclerView recyclerView = holder.getView(R.id.rc_item_rc_location);

        textView.setText(mStrings.get(position).getLetter());
        textView.setBackgroundColor(holder.getContext().getResources().getColor(R.color.minebackgroundColor));
        textView.setPadding(10, 1, 1, 1);

        if (1 == mStrings.get(position).getLetter().length()) {
            recyclerView.setLayoutManager(new LinearLayoutManager(holder.getContext()));
            recyclerView.addItemDecoration(new MyItemDecoration(holder.getContext(), LinearLayoutManager.VERTICAL, R.drawable.ic_draw_divider_3));
        }
        else {
            recyclerView.setLayoutManager(new GridLayoutManager(holder.getContext(),3));
        }
        ArrayList<String> mCityList = mStrings.get(position).getCityList();
        if (mCityList != null){
            recyclerView.setAdapter(new InGoneAdapter(mCityList,recyclerView));
        }
    }


    private class InGoneAdapter extends RcBaseAdapter<String>{
        private ArrayList<String>mCityList;
        private RecyclerView recyclerView;
        public InGoneAdapter(ArrayList<String> strings, RecyclerView recyclerView) {
            super(strings);
            mCityList = strings;
            this.recyclerView = recyclerView;
        }

        @Override
        public int getItemCount() {
            return mCityList.size();
        }

        @Override
        public int getItemViewType(int position) {

            int i = -1;
            if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                i = 0;
            }
            if(recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                i = 1;
            }
            Log.i("getItemViewType","i:"+i);
            return i;
        }

        @Override
        protected int setLayoutid(int viewType) {
            Log.i("setLayoutid","viewType:"+viewType);
            if (viewType == 0)
                return android.R.layout.simple_list_item_1;
            else
                return R.layout.item_rc_location_rc_item_1;

        }

        @Override
        protected void handleViewData(final RcBaseHodler holder, int position) {
            TextView view = null;
            Log.i("handleViewData","v  "+getItemViewType(position));
            if(getItemViewType(position) == 0){
                view = holder.getView(android.R.id.text1);
            }else {
                view = holder.getView(R.id.tv_item_rc_location_rc_item_1_text);
                view.setBackgroundColor(holder.getContext().getResources().getColor(R.color.write));
            }
            Log.i("handleViewData","position:"+position+"   getLayoutManager:"+recyclerView.getLayoutManager()+"   "+view.getId());
            view.setText(mCityList.get(position));
            view.setTextColor(holder.getContext().getResources().getColor(R.color.mineItemTextColor));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String city = ((TextView)v).getText().toString();
                    SpUtils.saveMessage(holder.getContext(),"Location","Lt",city);
                    TT.showText(city);
                    Intent intent = new Intent();
                    intent.putExtra("city",city);
                    ((LocationActivity)holder.getContext()).setResult(Activity.RESULT_OK,intent);
                    ((LocationActivity) holder.getContext()).finish();
                }
            });

        }
    }
}
