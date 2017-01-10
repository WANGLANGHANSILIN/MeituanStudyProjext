package com.wanglang.bottomnavigation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/11/9 0009.
 */

public abstract class RcBaseAdapter<T> extends RecyclerView.Adapter<RcBaseHodler> {

    private ArrayList<T> mTs;

    public RcBaseAdapter(ArrayList<T> ts) {
        mTs = ts;
    }

    @Override
    public RcBaseHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return RcBaseHodler.createViewHodler(parent.getContext(),parent,setLayoutid(viewType));
    }

    protected abstract int setLayoutid(int viewType);

    @Override
    public void onBindViewHolder(RcBaseHodler holder, int position) {
        handleViewData(holder,position);
    }

    protected abstract void handleViewData(RcBaseHodler holder, int position);

    @Override
    public int getItemCount() {
        return mTs.size();
    }

//    public void setData(ArrayList<T> data){
//        this.mTs = data;
//        notifyDataSetChanged();
//    }
}
