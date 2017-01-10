package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wanglang.bottomnavigation.R;

/**
 * Created by wangl on 2016/11/23 0023.
 */

public class MyIndicator extends View {

    private Paint mPaint,mBgPaint;
    private float mRadius = 12;
    private int mCount = 6;
    private int position = 0;
    private float mOffset = mRadius;
    private float mPadding = mRadius;
    private ViewPager viewPager;
    private float posOffset = 0.2f;


    public MyIndicator(Context context) {
        super(context);
    }

    public MyIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.indicator);
        mRadius = typedArray.getFloat(R.styleable.indicator_indicator_radius, mRadius);
        mCount = typedArray.getInteger(R.styleable.indicator_indicator_count,mCount);
        position = typedArray.getInteger(R.styleable.indicator_indicator_position,position);

        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);

        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(Color.GREEN);
        mBgPaint.setStrokeWidth(2);
        mBgPaint.setStyle(Paint.Style.FILL);
    }

    public void setViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setUpData(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setUpData(int position,float posOffset){
        this.position = position;
        this.posOffset = posOffset;
//        this.mOffset = posOffset+(position*mRadius*3);
        requestLayout();
        invalidate();
    }

    public void setCount(int count) {
        mCount = count;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mCount; i++) {
            canvas.drawCircle((mRadius+mPadding+mOffset)* (i+1) + (mRadius+mPadding) * i,(mRadius+mPadding+mOffset),mRadius,mPaint);
        }
        canvas.drawCircle((mRadius+mPadding+mOffset)* (position+1) + (mRadius+mPadding) * position+(mRadius+mPadding+mOffset+mRadius+mPadding)*posOffset,mRadius+mPadding+mOffset,mRadius,mBgPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        Log.i("onMeasure","widthMode:"+widthMode+"   heightMode:"+heightMode);
        if (widthMode == MeasureSpec.EXACTLY){
            MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec),widthMode);
        }

        if (heightMode == MeasureSpec.EXACTLY){
            MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec),heightMode);
        }
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

    }


}
