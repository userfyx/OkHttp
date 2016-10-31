package com.bw.test.myapplication.ViewClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.bw.test.myapplication.R;

/**
 * Created by a on 2016/10/31.
 */
public class GGL extends TextView {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();//手指滑动路径
    private Canvas mCanvas;//缓存画布
    private Bitmap mBitmap;//缓存图片
    private float pointX, pointY;//触点坐标

    public GGL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        initPaint();//初始化画笔

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //初始化Bitmap
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.parseColor("#c0c0c0"));//设置画板背景

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap mBackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        canvas.drawBitmap(mBackBitmap, 0, 0, null);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mCanvas.drawPath(mPath, mPaint);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointX = event.getX();
        pointY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(pointX, pointY);//将路径移动到点(pointX, pointY)，不绘制
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(pointX, pointY);//绘制一条从上个触点到点(pointX, pointY)的线条
                break;
        }
        invalidate();//重新绘图
        return true;
    }

    private void initPaint() {//初始化画笔
        mPaint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint.setAntiAlias(true);//设置抗锯齿
        mPaint.setStrokeWidth(30);
        mPaint.setColor(Color.RED);//设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆角
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
    }

}
