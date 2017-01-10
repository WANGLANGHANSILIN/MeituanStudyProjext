package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangl on 2016/12/8 0008.
 */

public class TorusView extends View {

    private Paint mCyclePaint;
    private float mStrokeWidth = 40;
    //文字大小
    private int textSize = 20;
    //分配比例大小，总比例大小为100,由于经过运算后最后会是99.55左右的数值，导致圆不能够重合，会留出点空白，所以这里的总比例大小我们用101
    private double[] strPercent = new double[]{16.67, 16.67, 16.67, 16.67, 16.67, 16.67};
    //边框颜色和标注颜色
    private int[] mColor = new int[]{0xFFF06292, 0xFF9575CD, 0xFFE57373, 0xFF4FC3F7, 0xFFFFF176, 0xFF81C784};
    //文字颜色
    private int textColor = 0xFF000000;

    private String[] str = new String[]{"一年级", "二年级", "三年级", "四年级", "五年级", "六年级"};
    private float mRadius = 300;
    private int mWidth;
    private int mHeight;
    private Paint textPaint;
    private Paint labelPaint;

    public TorusView(Context context) {
        super(context);
    }

    public TorusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TorusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mHeight = h;
        this.mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //移动画布到圆环的左上角
        canvas.translate(mWidth / 2 - mRadius / 2, mHeight / 2 - mRadius / 2);
        initPaint();

        drawCycle(canvas);

        drawText(canvas);

    }

    private void drawText(Canvas canvas) {
        for (int i = 0; i < str.length; i++) {
            textPaint.setColor(mColor[i]);
            canvas.drawText(str[i], (float) (1.2*mRadius+30),i*40,textPaint);
            labelPaint.setColor(mColor[i]);
            canvas.drawCircle((float) (1.2*mRadius+15),i*40-5,10,labelPaint);
        }
    }

    private void drawCycle(Canvas canvas) {
        float startPercent = 0;
        float sweepPercent = 0;
        RectF rectF = null;
        for (int i = 0; i < strPercent.length; i++) {
            mCyclePaint.setColor(mColor[i]);
            startPercent = sweepPercent + startPercent;
            //这里采用比例占100的百分比乘于360的来计算出占用的角度，使用先乘再除可以算出值
            sweepPercent = (float) (strPercent[i] * 360 / 100);
            rectF = new RectF(0, 0, mRadius, mRadius);
            canvas.drawArc(rectF, startPercent, sweepPercent, false, mCyclePaint);
        }
        textPaint.setColor(mColor[3]);
        textPaint.setStrokeWidth(10);
        canvas.drawCircle(rectF.width()/2,rectF.height()/2,60,textPaint);
        labelPaint.setColor(mColor[4]);
        String s= "Start";
        Rect rect = new Rect();
        labelPaint.getTextBounds(s,0,s.length(),rect);
        canvas.drawText(s, (rectF.width() - rect.width())/2,rectF.height()/2+(rect.height()/2), labelPaint);
    }

    private void initPaint() {
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
        //标注画笔
        labelPaint = new Paint();
        labelPaint = new Paint();
        labelPaint.setAntiAlias(true);
        labelPaint.setStyle(Paint.Style.FILL);
        labelPaint.setStrokeWidth(2);
    }
}
