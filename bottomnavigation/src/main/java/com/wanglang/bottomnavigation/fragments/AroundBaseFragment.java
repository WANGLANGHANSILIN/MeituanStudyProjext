package com.wanglang.bottomnavigation.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.adapters.RcBaseAdapter;
import com.wanglang.bottomnavigation.adapters.RcBaseHodler;
import com.wanglang.bottomnavigation.entitys.CategrayTagEntity;
import com.wanglang.bottomnavigation.utils.SpUtils;
import com.wanglang.bottomnavigation.utils.TT;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AroundBaseFragment extends Fragment {


    ArrayList<CategrayTagEntity> mArrTags;
    RecyclerView mRcFragAroundAll;
    private int mTag;

    private Handler mHandler;

    public AroundBaseFragment(ArrayList<CategrayTagEntity> mArrTags,int tag) {
        this.mArrTags = mArrTags;
        this.mTag = tag;
        mHandler = new Handler();
    }

    public AroundBaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_around_base, container, false);
        mRcFragAroundAll = (RecyclerView) view.findViewById(R.id.rc_frag_around_all);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRcFragAroundAll.setLayoutManager(new GridLayoutManager(getActivity(),4));

        mRcFragAroundAll.setAdapter(new RcBaseAdapter(mArrTags) {
            @Override
            protected int setLayoutid(int viewType) {
                return R.layout.item_tc_all_frag_arounf_frag;
            }


            //            SparseBooleanArray mSparseBooleanArray = new SparseBooleanArray(getItemCount());
            @Override
            protected void handleViewData(final RcBaseHodler holder, final int position) {

                holder.setText(R.id.tv_item_rc_all_frag_around_tag,mArrTags.get(position).getTagName());
                holder.setOnClickListener(R.id.tv_item_rc_all_frag_around_tag, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        for (int i = 0; i < mArrTags.size(); i++) {
                            for (int i1 = 0; i1 < getItemCount(); i1++) {
                                mRcFragAroundAll.getChildAt(i1).setSelected(false);
                            }
                            mArrTags.get(position).setiSeletor(false);
                        }

                        Log.i("setOnClickListener","id:"+getItemId(position)+"position:"+position+"   "+v.isSelected()+"   "+String.valueOf(position));

                        mRcFragAroundAll.getChildAt(position).setSelected(true);
                        mArrTags.get(position).setiSeletor(true);
                        SpUtils.saveSigleMessage(holder.getContext(),"TagName","TagPostion"+mTag,String.valueOf(position));
                        Log.e("handleViewData","2key:TagPostion"+mTag +"   tagPostion:"+String.valueOf(position));
                        /*
                        if (!mArrTags.get(position).iSeletor()){

                        }else {
                            mRcFragAroundAll.getChildAt(position).setSelected(false);
                            mArrTags.get(position).setiSeletor(false);
                        }
                        */

                        TT.showText(mArrTags.get(position).getTagName());
                    }
                });
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

        });

    }

    @Override
    public void onStart() {
        super.onStart();

        if (mHandler != null){

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String tagPostion = SpUtils.getSigleMessage(getActivity(), "TagName", "TagPostion"+mTag);
                    int pos = 0;
                    if (tagPostion != null){
                        pos = Integer.valueOf(tagPostion);
                    }
                    try {
                        Log.e("handleViewData","1key:"+"   TagPostion"+mTag+"   pos:"+pos + "   is null ? "+(mRcFragAroundAll.getChildAt(pos) == null));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    if (mRcFragAroundAll.getChildAt(pos) != null) {
                        mArrTags.get(pos).setiSeletor(true);
                        mRcFragAroundAll.getChildAt(pos).setSelected(true);
                    }
                }
            },300);
        }
    }
}
