package com.wanglang.bottomnavigation.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.adapters.RcBaseAdapter;
import com.wanglang.bottomnavigation.adapters.RcBaseHodler;
import com.wanglang.bottomnavigation.view.MyItemDecoration;

import java.util.ArrayList;

public class ShopDetailActivity extends AppCompatActivity {

    private RecyclerView mRcShopDerail;
    private ArrayList<String> mArrayList;
    private float mDownY;
    private float mMoveY;
    private ImageView mImageView;
    private int mImageViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        mRcShopDerail = (RecyclerView) findViewById(R.id.rc_shop_derail);
        mRcShopDerail.setLayoutManager(new LinearLayoutManager(this));
        mRcShopDerail.addItemDecoration(new MyItemDecoration(this,LinearLayoutManager.VERTICAL,R.drawable.ic_drawable_divider1));


        mImageView = (ImageView) findViewById(R.id.iv_shop_derail);
        mImageView.setImageResource(R.mipmap.ic_category_11);
        mImageViewHeight = mImageView.getHeight();
        mArrayList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mArrayList.add("我是数据："+i+1);
        }

        mRcShopDerail.setAdapter(new RcBaseAdapter(mArrayList) {
            @Override
            protected int setLayoutid(int viewType) {
                return android.R.layout.simple_list_item_1;
            }

            @Override
            protected void handleViewData(RcBaseHodler holder, int position) {
                holder.setText(android.R.id.text1, mArrayList.get(position));
            }
        });

        mRcShopDerail.addItemDecoration(new MyItemDecoration(this,LinearLayoutManager.VERTICAL));

        /*
        mRcShopDerail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("onScrollStateChanged","newState:"+newState);
        }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("onScrolledChange","dx:"+dx+"   dy:"+dy);
            }
        });

        mRcShopDerail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float y = event.getY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mDownY = y;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        mMoveY = y;
                        float v1 = (mMoveY - mDownY) / 2;
                        Log.i("onTouch","mDownY:"+mDownY+"   mMoveY:"+mMoveY+"   v1:"+v1+"   mImageViewHeight:"+mImageViewHeight);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
                        params.height = (int) (mImageView.getHeight() + v1);
                        if (mMoveY > mDownY){//向下滑动
                            if (params.height > 0){
                                mImageView.setVisibility(View.VISIBLE);
                            }
                        }else{//向上滑动
                            if (params.height <= 0){
                                mImageView.setVisibility(View.GONE);
                            }
                        }
                        mImageView.setLayoutParams(params);
                        mImageView.requestLayout();
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
         */
    }
}
