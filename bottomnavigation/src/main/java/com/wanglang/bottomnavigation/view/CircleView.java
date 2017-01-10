package com.wanglang.bottomnavigation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.R.attr.radius;

/**
 * Created by wangl on 2016/12/7 0007.
 */

public class CircleView extends View {

    private Paint mPaint,mPaint1;
    public static final int[] SWEEP_GRADIENT_COLORS = new int[]{Color.GREEN, Color.GREEN, Color.BLUE, Color.RED, Color.RED};

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5);

        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setColor(Color.YELLOW);
        mPaint1.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawCircle(100,100,50,mPaint);
//
//        canvas.drawCircle(100,100,30,mPaint1);
        drawArc(canvas);
    }

    private void drawArc(Canvas canvas) {
        RectF rectF = new RectF(200, 200, 800, 800);
        Log.d("yang","left:" + rectF.left + "top:" +rectF.top + "right:" + rectF.right + "bottom:" + rectF.bottom);
        mPaint.setColor(Color.parseColor("#33333333"));
        //oval :指定圆弧的外轮廓矩形区域。
        //startAngle: 圆弧起始角度，单位为度。从180°为起始点
        //sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度。
        //useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。如果false会将圆弧的两端用直线连接
        //paint: 绘制圆弧的画板属性，如颜色，是否填充等
        // public void drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter,@NonNull Paint paint)
        canvas.drawRect(rectF,mPaint);
        mPaint.setColor(Color.parseColor("#ff0000"));
        SweepGradient mColorShader = new SweepGradient(radius, radius,SWEEP_GRADIENT_COLORS,null);
        mPaint.setShader(mColorShader);
        canvas.drawArc(rectF,-180,120,true,mPaint);
//        float dx = (getWidth() - mTableRectF.width()) / 2;
//        float dy = (getHeight() - mTableRectF.height()) / 2;
//        //把油表的方框平移到正中间
//        canvas.translate(dx, dy);
//        canvas.save();
//
//        canvas.rotate(120,500,500);
//        canvas.restore();
    }
}
