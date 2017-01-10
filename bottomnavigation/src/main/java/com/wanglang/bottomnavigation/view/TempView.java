package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by wangl on 2016/12/16 0016.
 */

public class TempView extends View {

    private int height;
    private int width;
    private int dailRadius;
    private int arcRadius;
    private int scaleHeight = dp2px(10);

    private String title = "最高温度设置";


    private Paint dailPaint;
    private Paint arcPaint;
    private Paint titlePaint;
    private Paint tempFalgPaint;
    private Paint tempPaint;


    public TempView(Context context) {
        this(context,null);
    }

    public TempView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TempView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dailPaint = new Paint();
        dailPaint.setAntiAlias(true);
        dailPaint.setStrokeWidth(dp2px(2));
        dailPaint.setStyle(Paint.Style.STROKE);

        arcPaint = new Paint(dailPaint);
        arcPaint.setColor(Color.parseColor("#3CB7EA"));

        titlePaint = new Paint();
        titlePaint.setTextSize(sp2px(15));
        titlePaint.setColor(Color.parseColor("#3B434E"));
        titlePaint.setAntiAlias(true);
        titlePaint.setStyle(Paint.Style.STROKE);

        tempFalgPaint = new Paint(titlePaint);
        titlePaint.setColor(Color.parseColor("#E4A07E"));
        tempFalgPaint.setTextSize(sp2px(25));

        tempPaint = new Paint(tempFalgPaint);
        titlePaint.setColor(Color.parseColor("#E27A3F"));
        tempFalgPaint.setTextSize(sp2px(60));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = height = Math.max(w,h);
        dailRadius = width/2 - dp2px(10);
        arcRadius = dailRadius - dp2px(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDail(canvas);
        drawArc(canvas);
        drawTagAndTitle(canvas);
    }

    private void drawTagAndTitle(Canvas canvas) {
        canvas.save();
        canvas.translate(width/2,height/2);

        canvas.drawLine(width,0,-width,0,titlePaint);
        canvas.drawLine(0,-width,0,width,titlePaint);

        Rect rect = new Rect();
        titlePaint.getTextBounds(title,0,title.length(),rect);
        float textWidth = titlePaint.measureText(title,0,title.length());
        canvas.drawText(title,(-width/2+rect.width()),arcRadius,titlePaint);

        String minFlag = 10+"";
        canvas.rotate(-55, width / 2, height / 2);
        canvas.drawText(minFlag,-dailRadius+dp2px(20),0,titlePaint);

//        canvas.rotate(45, width / 2, height / 2);
        String maxFlag = 30+"";
        canvas.drawText(maxFlag,dailRadius-dp2px(00),30,titlePaint);

        canvas.restore();
    }

    private void drawDail(Canvas canvas) {
        canvas.save();
        canvas.translate(width/2,height/2);
        canvas.rotate(-202);
        dailPaint.setColor(Color.parseColor("#3CB7EA"));
        for (int i = 0; i < 60; i++) {
            canvas.rotate(3.70f);
            canvas.drawLine(dailRadius,0,dailRadius-dp2px(10),0,dailPaint);
        }
        canvas.restore();
    }

    private void drawArc(Canvas canvas) {
        canvas.save();
        canvas.translate(width/2,height/2);
        RectF rectF = new RectF(-arcRadius,-arcRadius,arcRadius,arcRadius);
        canvas.rotate(-202);
        canvas.drawArc(rectF,0,225,false,arcPaint);
        canvas.restore();
    }

    private int dp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());
    }

    private int sp2px(float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,value,getResources().getDisplayMetrics());
    }


}
