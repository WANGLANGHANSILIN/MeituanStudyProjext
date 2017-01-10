package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.wanglang.bottomnavigation.R;

/**
 * Created by wangl on 2016/12/29 0029.
 */

public class LoopView extends View {

    private Paint mPaint;
    private Paint labelPaint;
    private int mHeight;
    private int mWith;
    private Bitmap mBitmap;
    private Rect mSrcRect;
    private Rect mDestRect;
    private int mBitWidth;
    private int mBitHeight;
    private Paint mBitPaint;

    public LoopView(Context context) {
        this(context,null);
    }

    public LoopView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(6);
        /*

        //边框画笔
        mCyclePaint = new Paint();
        mCyclePaint.setAntiAlias(true);
        mCyclePaint.setStyle(Paint.Style.STROKE);
        mCyclePaint.setStrokeWidth(mStrokeWidth);



        //文字画笔
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(textSize);
        */

        //标注画笔
//        labelPaint = new Paint();
        labelPaint = new Paint();
        labelPaint.setAntiAlias(true);
        labelPaint.setStyle(Paint.Style.FILL);
        labelPaint.setStrokeWidth(2);
        labelPaint.setColor(Color.YELLOW);



        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);


        initBitmap();
    }

    private void initBitmap() {
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_category_1);
        mBitmap = ((BitmapDrawable) drawable).getBitmap();
        mBitWidth = mBitmap.getWidth();
        mBitHeight = mBitmap.getHeight();
        mSrcRect = new Rect(0, 0, mBitWidth, mBitHeight);
        mDestRect = new Rect(dp2px(40),dp2px(40), dp2px(75),dp2px(75));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCircle(canvas);


    }

    private void drawCircle(Canvas canvas) {
        canvas.save();

        canvas.translate(mWith/2,mHeight/2);
        canvas.save();


        canvas.drawCircle(0,0,dp2px(100),mPaint);



        for (int i = 0; i < 8; i++) {
//            canvas.drawCircle(0,dp2px(70),dp2px(30),labelPaint);
            canvas.restore();
            canvas.rotate(45);
            canvas.drawBitmap(mBitmap,mSrcRect,mDestRect,mBitPaint);
            canvas.save();
        }

        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWith = w;
        mHeight = h;
    }

    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,getResources().getDisplayMetrics());
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,getResources().getDisplayMetrics());
    }


    private class CirView extends View{

        public CirView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setColor(Color.RED);
            canvas.drawCircle(20,20,10,mPaint);
        }
    }
}
