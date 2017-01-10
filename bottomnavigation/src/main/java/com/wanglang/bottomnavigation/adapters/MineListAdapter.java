package com.wanglang.bottomnavigation.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.entitys.PersonInfoEntity;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/11/30 0030.
 */

public class MineListAdapter extends RcBaseAdapter<PersonInfoEntity> implements View.OnClickListener{

    private ArrayList<PersonInfoEntity> mEntities;
    private Context mContext;
    private OnItemCickListener listener;

    public MineListAdapter(ArrayList<PersonInfoEntity> arrayList, Context context) {
        super(arrayList);
        this.mEntities = arrayList;
        this.mContext = context;
    }


    @Override
    protected int setLayoutid(int viewType) {
        if (viewType == 0)
            return R.layout.item_rc_mine_header;

        else if(viewType == 1)
            return R.layout.item_rc_mine_qita_;

        else
            return R.layout.item_rc_mine_;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        if (position == 1) return 1;
        return 2;
    }

    @Override
    public int getItemCount() {
        return mEntities.size()+1+1;
    }

    @Override
    protected void handleViewData(RcBaseHodler holder, int position) {
        int itemViewType = getItemViewType(position);
        Log.i("","handleViewData: itemViewType:"+itemViewType+" ,position:"+position);
        if (itemViewType == 0 && position == 0){
            holder.getView(R.id.tv_item_mine_login).setOnClickListener(new HandleOnClickTouchEvent(position,null));
            holder.getView(R.id.iv_item_mine_header_icon).setOnClickListener(new HandleOnClickTouchEvent(position,null));
            holder.getView(R.id.iv_item_mine_coolect).setOnClickListener(new HandleOnClickTouchEvent(position,null));
            holder.getView(R.id.iv_item_mine_stock).setOnClickListener(new HandleOnClickTouchEvent(position,null));
        }
        else if (itemViewType == 1 && position == 1){
            holder.getView(R.id.tv_item_mine_stay_payment).setOnClickListener(new HandleOnClickTouchEvent(position,null));
            holder.getView(R.id.tv_item_mine_stay_use).setOnClickListener(new HandleOnClickTouchEvent(position,null));
            holder.getView(R.id.tv_item_mine_stay_evaluate).setOnClickListener(new HandleOnClickTouchEvent(position,null));
            holder.getView(R.id.tv_item_mine_refund).setOnClickListener(new HandleOnClickTouchEvent(position,null));
        }else if(itemViewType == 2){
            int pos = position > 1?position-2:position;
            PersonInfoEntity personInfoEntity = mEntities.get(pos);
            holder.setText(R.id.tv_item_mine_name,personInfoEntity.getPiName());
            holder.setImageResource(R.id.iv_item_mine_icon,personInfoEntity.getPimgId());
            holder.setText(R.id.tv_item_mine_text,personInfoEntity.getPiTextDec());
            View view = holder.getView(R.id.space_item_mine_s);
            if (position == 6 || position == 10){
                view.setVisibility(View.VISIBLE);
            }
            Log.i("handleViewData","PersonInfoEntity:11  "+personInfoEntity.getPiName()+"   pos:"+pos);
            holder.getView(R.id.rl_item_mine).setOnClickListener(new HandleOnClickTouchEvent(pos,personInfoEntity));
        }
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnItemCickListener{
        public void onClick(View v, int pos, PersonInfoEntity personInfoEntity);
    }

    public void setOnItemCickListener(OnItemCickListener listener){
        this.listener = listener;
    }

    private class HandleOnClickTouchEvent implements View.OnClickListener {
        private int pos;
        private PersonInfoEntity personInfoEntity;
        public HandleOnClickTouchEvent(int position,PersonInfoEntity personInfoEntity) {
            this.pos = position;
            this.personInfoEntity = personInfoEntity;
        }

        @Override
        public void onClick(View v) {
            try {
                Log.e("handleViewData","PersonInfoEntity:22  "+personInfoEntity.getPiName()+"   pos:"+pos);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            listener.onClick(v,pos,personInfoEntity);
        }
    }
}
