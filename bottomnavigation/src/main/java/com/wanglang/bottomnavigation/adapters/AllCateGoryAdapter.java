package com.wanglang.bottomnavigation.adapters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.entitys.CategoryEntity;
import com.wanglang.bottomnavigation.utils.TT;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/12/9 0009.
 */

public class AllCateGoryAdapter extends RcBaseAdapter<CategoryEntity> {

    private ArrayList<CategoryEntity> mCategoryEntities;
    private static int pos = -1;
    private String mFistCateName;


    public AllCateGoryAdapter(ArrayList<CategoryEntity> categoryEntities) {
        super(categoryEntities);
        this.mCategoryEntities = categoryEntities;
    }

    @Override
    protected int setLayoutid(int viewType) {
        return R.layout.item_rc_allcategory_title;
    }


    @Override
    protected void handleViewData(RcBaseHodler holder, int position) {
        {
            pos = position;
            Log.e("handleViewData","pos:"+pos+"  mCategoryEntities:"+mCategoryEntities.size()+"   position:"+position);
            CategoryEntity categoryEntity = mCategoryEntities.get(pos);
            Log.e("handleViewData","pos:"+pos+"  id:"+categoryEntity.getId()+""+categoryEntity.getName());
            TextView tvButtom = holder.getView(R.id.tv_item_allcate_buttom);
            ImageView ivIcon = holder.getView(R.id.iv_item_allcate_icon);
            RecyclerView rcList = holder.getView(R.id.rc_item_allcate);
            TextView tvTitle =  holder.getView(R.id.tv_item_allcate_title);

            if (categoryEntity.getId() != -1)
            {
                tvButtom.setVisibility(View.GONE);
                holder.getView(R.id.ll_item_allcate_ll).setVisibility(View.VISIBLE);
                rcList.setVisibility(View.VISIBLE);

                tvTitle.setText(categoryEntity.getName());
                Glide.with(holder.getContext()).load(categoryEntity.getIconUrl()).into(ivIcon);

                rcList.setLayoutManager(new GridLayoutManager(holder.getContext(), 4));
                ArrayList<CategoryEntity.ListBean>mListBeenAfter = new ArrayList<>();
//                recyclerView.addItemDecoration(new MyItemDecoration(holder.getContext(), LinearLayoutManager.VERTICAL,R.drawable.ic_drawable_divider1));
                if (categoryEntity.getList().size() > 12 && !isOclick(categoryEntity.getList())){
                    categoryEntity.getList().add(11,new CategoryEntity.ListBean(">>"));
                }

                rcList.setAdapter(new SubCallGoryAdapter(categoryEntity.getList()));
            }else
            {
                mFistCateName = categoryEntity.getName();
                if (pos == mCategoryEntities.size()-1){
                    tvButtom.setVisibility(View.VISIBLE);
                }else
                    tvButtom.setVisibility(View.GONE);
                holder.getView(R.id.ll_item_allcate_ll).setVisibility(View.GONE);
                rcList.setVisibility(View.GONE);
            }

            if (pos == mCategoryEntities.size()-1){
                tvButtom.setVisibility(View.VISIBLE);

                tvButtom.setText(mFistCateName);
                tvButtom.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TT.showText(((TextView) v).getText().toString());
                    }
                });
            }else
                tvButtom.setVisibility(View.GONE);
        }



    }

    private ArrayList<CategoryEntity.ListBean> handleData(CategoryEntity categoryEntity,ArrayList<CategoryEntity.ListBean> mListBeenAfter) {
        if (categoryEntity.getList() == null){
            return null;
        }

        ArrayList<CategoryEntity.ListBean> list = categoryEntity.getList();
//        for (int i = 0; i < list.size(); i++) {
//            Log.e("handleData",""+(!list.get(i).getName().equals(">>"))+"   "+list.size());
//
//            Log.e("handleData",""+(list.size() > 11 && !list.get(i).getName().equals(">>")));
//            if (categoryEntity.getList().size() > 11 && !categoryEntity.getList().get(i).getName().equals(">>")){
//                list.add(11,new CategoryEntity.ListBean(">>"));
//                break;
//            }
//        }

        ArrayList<CategoryEntity.ListBean> mMListBeenBrfore = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i<11){
                mListBeenAfter.add(list.get(i));
            }else{
                mMListBeenBrfore.add(list.get(i));
            }
        }

        return mMListBeenBrfore;
    }


    public static boolean isOclick(ArrayList<CategoryEntity.ListBean> mlListBeen){

        for (CategoryEntity.ListBean listBean : mlListBeen) {
            if (listBean.getName().equals(">>"))
                return true;
        }
        return false;
    }

    private class SubCallGoryAdapter extends RcBaseAdapter<CategoryEntity.ListBean>{


        private ArrayList<CategoryEntity.ListBean> mListBeen;

        public SubCallGoryAdapter(ArrayList<CategoryEntity.ListBean> listBeen) {
            super(listBeen);
            this.mListBeen = listBeen;
        }

        private void setData(ArrayList<CategoryEntity.ListBean> listBeen){
            mListBeen = listBeen;
            notifyDataSetChanged();
        }

        @Override
        protected int setLayoutid(int viewType) {
            return R.layout.rc_item_allcate_rc_item_1;
        }



        @Override
        public int getItemCount() {
            if (mListBeen.size() > 12 && isOclick(mListBeen)){
                return 12;
            }else {
                return mListBeen.size();
            }
        }



        @Override
        protected void handleViewData(RcBaseHodler holder, int position) {
            TextView tv_text = holder.getView(R.id.tv_item_allcate_rc_item_1);
            Log.e("handleViewData", "position:" + position+"   "+mListBeen.size());
            if (position < mListBeen.size()) {
                CategoryEntity.ListBean listBean = mListBeen.get(position);
                tv_text.setText(listBean.getName());
                tv_text.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String toString = ((TextView) v).getText().toString();
                        TT.showText(toString);
                        if (toString.equals(">>")){
                            mListBeen.remove(11);
                            notifyDataSetChanged();
                            Log.e("handleViewData", "position:  dds  " + mListBeen.size());
//                            mListBeen.addAll(11,mListBeenAfter);
//                            setData(mListBeen);
                        }
                    }
                });
            }
        }
    }
}
