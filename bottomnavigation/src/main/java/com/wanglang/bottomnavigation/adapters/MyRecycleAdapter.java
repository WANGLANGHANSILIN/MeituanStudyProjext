package com.wanglang.bottomnavigation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.entitys.RecomenderEntity;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/11/9 0009.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter{

    private ArrayList<RecomenderEntity> entities;
    private OnRClickListener mListener;

    public MyRecycleAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcview,parent,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecomenderEntity recomenderEntity = entities.get(position);
        ((MyViewHodler)holder).mTv_pref.setText(recomenderEntity.getRePreTitle());
        ((MyViewHodler)holder).mTv_recomen.setText(recomenderEntity.getReTitle());
        ((MyViewHodler)holder).mIvType.setImageResource(recomenderEntity.getReid());
        holder.itemView.setOnClickListener(new MyOnClick(position));
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public void setData(ArrayList<RecomenderEntity> entities){
        this.entities = entities;
        notifyDataSetChanged();
    }

    class MyOnClick implements View.OnClickListener{
        private int position;
        public MyOnClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            mListener.OnRClick(position);
        }
    }
    public interface OnRClickListener{
        public void OnRClick(int position);
    }
    public void setOnRClickListener(OnRClickListener onRClickListener){
        this.mListener = onRClickListener;
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        private TextView mTv_pref,mTv_recomen;
        private ImageView mIvType;

        public MyViewHodler(View itemView) {
            super(itemView);
            itemView.setBackgroundResource(R.drawable.ic_draw_item_bg);
            initView(itemView);
        }

        private void initView(View itemView) {
            mTv_pref = (TextView) itemView.findViewById(R.id.tv_item_pref);
            mTv_recomen = (TextView) itemView.findViewById(R.id.tv_item_recomen);
            mIvType = (ImageView) itemView.findViewById(R.id.iv_item_type);
        }
    }
}
