package com.wanglang.bottomnavigation.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by wangl on 2016/11/9 0009.
 */

public class RcBaseHodler extends RecyclerView.ViewHolder {

    private View convertView;
    private Context mContext;

    private SparseArray<View> mViews;

    public RcBaseHodler(Context context,View itemView) {
        super(itemView);
        this.mContext = context;
        this.convertView = itemView;
        mViews = new SparseArray<>();
    }

    public static RcBaseHodler createViewHodler(Context context,View itemView){
        return new RcBaseHodler(context,itemView);
    }

    //创建ViewHodle
    public static RcBaseHodler createViewHodler(Context context, ViewGroup parent,int layId){
       View view = LayoutInflater.from(context).inflate(layId,parent,false);
        return createViewHodler(context,view);
    }

    //根据viewid获取控件
    public <T extends View>T getView(int viewId){

        View view = mViews.get(viewId);
        if (view == null){
            view = convertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }


    public Context getContext() {
        return mContext;
    }
    ///////////////////////////常用控件设置/////////////////////////////////////////////

    //设置Text
    public RcBaseHodler setText(int redId, String text){
        TextView textView = getView(redId);
        textView.setText(text);
        return this;
    }

    public String getText(int redId){
        return ((TextView)getView(redId)).getText().toString();
    }

    //设置图片icon
    public RcBaseHodler setImageResource(int viewId,int imgId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(imgId);
        imageView.getImageAlpha();
        return this;
    }

    //设置点击事件
    public RcBaseHodler setOnClickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    //设置长按事件
    public RcBaseHodler setOnLongClickListener(int viewId,View.OnLongClickListener listener){
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public RcBaseHodler setOnTouchClickListener(int viewId,View.OnTouchListener listener){
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }



    public RcBaseHodler setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public RcBaseHodler setImageDrawable(int viewId, Drawable drawable)
    {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public RcBaseHodler setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public RcBaseHodler setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public RcBaseHodler setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RcBaseHodler setTextColorRes(int viewId, int textColorRes)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public RcBaseHodler setTextSize(int viewId, int size)
    {
        TextView view = getView(viewId);
        view.setTextSize(size);
        return this;
    }

    @SuppressLint("NewApi")
    public RcBaseHodler setAlpha(int viewId, float value)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            getView(viewId).setAlpha(value);
        } else
        {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public RcBaseHodler setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public RcBaseHodler linkify(int viewId)
    {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public RcBaseHodler setTypeface(Typeface typeface, int... viewIds)
    {
        for (int viewId : viewIds)
        {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public RcBaseHodler setProgress(int viewId, int progress)
    {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public RcBaseHodler setProgress(int viewId, int progress, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public RcBaseHodler setMax(int viewId, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public RcBaseHodler setRating(int viewId, float rating)
    {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public RcBaseHodler setRating(int viewId, float rating, int max)
    {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public RcBaseHodler setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public RcBaseHodler setTag(int viewId, int key, Object tag)
    {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public RcBaseHodler setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

}
