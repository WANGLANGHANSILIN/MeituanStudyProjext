package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangl on 2016/12/1 0001.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration{

    private int mDividerHeight = 2;
    private int[] mAttrs = {android.R.attr.listDivider};
    private Drawable mDivider;
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;
    private int orientation;

    public MyItemDecoration(Context context,int orientation) {
        TypedArray typedArray = context.obtainStyledAttributes(mAttrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public MyItemDecoration(Context context,int orientation,int drawID) {
        this(context,orientation);
        mDivider = ContextCompat.getDrawable(context, drawID);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    private void setOrientation(int orientation) {
        if (HORIZONTAL != orientation && VERTICAL != orientation){
            throw new IllegalArgumentException("invalid orientation");
        }
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (orientation == HORIZONTAL){
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }else if(orientation == VERTICAL){
            outRect.set(0,0,0,mDividerHeight);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == HORIZONTAL){
            drawHorizontal(c,parent);
        }else if(orientation == VERTICAL){
            drawVertical(c,parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int top = view.getBottom()+layoutParams.bottomMargin+Math.round(ViewCompat.getTranslationY(view));
            int bottom = top + mDividerHeight;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int left = view.getRight()+layoutParams.rightMargin+Math.round(ViewCompat.getTranslationX(view));
            int right = left + mDividerHeight;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
}
