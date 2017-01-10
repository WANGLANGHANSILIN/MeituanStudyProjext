package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by wangl on 2016/12/6 0006.
 */

public class ParallaxView extends LinearLayout{
    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private int mImageViewHeight;
    private float mDownY;
    private float mMoveY;
    private ViewGroup.LayoutParams mParams;

    public ParallaxView(Context context) {
        super(context);
    }

    public ParallaxView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ParallaxView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View childAt = getChildAt(0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof ImageView){
                mImageView = (ImageView) getChildAt(i);
                mImageViewHeight = mImageView.getMeasuredHeight();
            }else if (getChildAt(i) instanceof  RecyclerView){
                mRecyclerView = (RecyclerView) getChildAt(i);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        boolean iSIntercept = false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                mMoveY = y;
                float v1 = (mMoveY - mDownY) / 2;
                Log.e("onTouch","mDownY:"+mDownY+"   mMoveY:"+mMoveY+"   v1:"+v1+"   mImageViewHeight:"+mImageViewHeight+"   "+mImageView.getHeight());
                mParams = mImageView.getLayoutParams();
                mParams.height = (int) (mImageView.getHeight() + v1);
                if (mMoveY > mDownY){//向下滑动
                    if (mParams.height > 0){
                        mImageView.setVisibility(View.VISIBLE);
                    }
                }else{//向上滑动
                    if (mParams.height <= 0){
                        mImageView.setVisibility(View.GONE);
                        mRecyclerView.requestDisallowInterceptTouchEvent(true);
                        iSIntercept = true;
                        break;
                    }
                }
                mImageView.setLayoutParams(mParams);
                mImageView.requestLayout();
                break;

            case MotionEvent.ACTION_UP:

                if (mParams.height > mImageViewHeight){
                    mParams.height = mImageViewHeight;
                }
                mImageView.setLayoutParams(mParams);
                mImageView.requestLayout();
                break;
        }
        return iSIntercept;
    }
}
