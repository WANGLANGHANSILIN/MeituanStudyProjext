package com.wanglang.bottomnavigation.adapters;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.activitys.ShopDetailActivity;
import com.wanglang.bottomnavigation.entitys.NoticeEntity;
import com.wanglang.bottomnavigation.entitys.RecomenderEntity;
import com.wanglang.bottomnavigation.utils.TT;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/11/10 0010.
 */

public class RcNoticeAdapter extends RcBaseAdapter<NoticeEntity> {

    private ArrayList<NoticeEntity> ts;
    private ArrayList<RecomenderEntity> mRecomenderEntities;
    public RcNoticeAdapter(ArrayList<NoticeEntity> noticeEntities, ArrayList<RecomenderEntity> recomenderEntities){
        super(noticeEntities);
        this.ts = noticeEntities;
        this.mRecomenderEntities = recomenderEntities;
    }
    @Override
    protected int setLayoutid(int viewType) {
        if (viewType == 0)
            return R.layout.item_rc_location_2;
        else
            return R.layout.item_notices_ll;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else
            return 1;
    }

    @Override
    public void handleViewData(final RcBaseHodler holder, int position) {
        if (position == 0)
        {
            holder.getView(R.id.tv_item_rc_location_around).setVisibility(View.GONE);
            RecyclerView mRcTab = holder.getView(R.id.rc_item_rc_location_2_rc);
            mRcTab.setBackgroundColor(holder.getContext().getResources().getColor(R.color.write));
            mRcTab.setLayoutManager(new GridLayoutManager(holder.getContext(),3));
            MyRecycleAdapter mRecycleAdapter = new MyRecycleAdapter();
            mRcTab.setAdapter(mRecycleAdapter);

            mRecycleAdapter.setData(mRecomenderEntities);
            mRecycleAdapter.setOnRClickListener(new MyRecycleAdapter.OnRClickListener() {
                @Override
                public void OnRClick(int position) {
                    Log.i("OnRClick", "position:" + position);
                    String reTitle = mRecomenderEntities.get(position).getReTitle();
                    TT.showText(reTitle);
                }
            });
        }else
        {
            holder.setImageResource(R.id.iv_notice_icon,ts.get(position).getIconId());
            holder.setText(R.id.tv_notice_name,ts.get(position).getNoticeName());
            holder.setText(R.id.tv_notice_dec,ts.get(position).getNoticeAddress());
            holder.setText(R.id.tv_notice_price,ts.get(position).getNoticePrice());
            holder.setText(R.id.tv_notice_range,ts.get(position).getNoticeRange()+"km");
            holder.setText(R.id.tv_notice_sold,"已售"+ts.get(position).getGetNoticeSold());

            holder.setOnClickListener(R.id.rl_item_notice, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TT.showText( holder.getText(R.id.tv_notice_name));
                    v.setBackgroundResource(R.drawable.ic_draw_item_bg);
                    holder.getContext().startActivity(new Intent(holder.getContext(), ShopDetailActivity.class));
                }
            });
        }
    }
}
