package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/12/12 0012.
 */

public class SortView extends View {
    private ArrayList<String> letters;
    private int width;
    private int height;
    private Paint mPaint;

    private OnScrollMoveListener mListener;
    private int mY;

    public SortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SortView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SortView(Context context) {
        this(context,null);
    }

    private void init() {
        letters = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(30);
    }

    public void setLetters(ArrayList<String> letters){
        this.letters = letters;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < letters.size(); i++) {
            String letter = letters.get(i);

            Rect mTextRect = new Rect();
            mPaint.getTextBounds(letter, 0, letter.length(), mTextRect);
            int x = width/2 - mTextRect.width()/2;
            int y;
            mY = (height-getPaddingTop() - getPaddingBottom())/letters.size();
            y = mY;
            y = (int) (mTextRect.height()+ y *(i+0.5));
//            Log.e("onDraw","mY:"+mY);
            canvas.drawText(letter,x, y,mPaint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float downY = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                Log.e("down","   downY:"+downY +"    "+ event.getX());
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();

                float v = (moveY / mY);
                if (v > letters.size()-1){
                    v = letters.size()-1;
                }
                Log.e("onTouchEvent","pos2:"+(int)v);
                mListener.onMove(letters.get((int) v));
                break;
            case MotionEvent.ACTION_UP:
                mListener.onMove("");
                Log.e("up","   downY:"+downY+"   moveY:"+event.getY()+"    "+ getX());
                break;
        }
        return true;
    }

    public interface OnScrollMoveListener{
        public void onMove(String s);
    }


    public OnScrollMoveListener getListener() {
        return mListener;
    }

    public void setListener(OnScrollMoveListener listener) {
        mListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
