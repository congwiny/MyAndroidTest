package com.congwiny.giftanim.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Date;
import java.util.Random;

public class HeartView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mSurfaceHolder;
    private boolean isRun = true;
    private Canvas mCanvas;
    public static int X, Y;
    private Paint mPaint;
    private int mCurrentDistance = 0;
    private Random mRandom = new Random();

    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        setPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        X = getMeasuredWidth() / 2;
        Y = getMeasuredHeight();
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRun = false;
    }

    @Override
    public void run() {
        Date date = null;
        PointF pointF = null;
        PointF pointF1 = new PointF(mRandom.nextInt(X), mRandom.nextInt(Y / 2));
        PointF pointF2 = new PointF(mRandom.nextInt(X / 2), mRandom.nextInt(Y));

        while (isRun) {

            date = new Date();
            try {
                mCanvas = mSurfaceHolder.lockCanvas(null);
                if (mCanvas != null) {
                    synchronized (mSurfaceHolder) {
                        // 清屏
                        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        if (mCurrentDistance >= Y) {
                            mCurrentDistance = 0;
                            pointF1 = new PointF(mRandom.nextInt(X*2), mRandom.nextInt(Y / 2));
                            pointF2 = new PointF(mRandom.nextInt(X ), mRandom.nextInt(Y));
                        }
                        pointF = calculateBezierPoint(mCurrentDistance * 1.0f / Y, pointF1, pointF2);
//                        Log.e("point", "pointF(x,y)=" + "(" + pointF.x + "," + pointF.y + ")");
                        mCanvas.drawCircle(pointF.x, pointF.y, 20, mPaint);
                        mCurrentDistance += 10;
                        // 控制帧数
                        Thread.sleep(Math.max(0, 30 - (new Date().getTime() - date.getTime())));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mCanvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }

    /**
     * @param t      时间
     * @param point1 控制点1
     * @param point2 控制点2
     * @return
     */
    public PointF calculateBezierPoint(float t, PointF point1, PointF point2) {
        float oneMinusT = 1.0f - t;
        PointF point = new PointF();

        PointF point0 = new PointF();
        point0.set(X, 0);

//        PointF point1 = new PointF();
//        point1.set(X, 0);
//
//        PointF point2 = new PointF();
//        point2.set(0, Y);

        PointF point3 = new PointF(X, Y);

        point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                + 3 * oneMinusT * oneMinusT * t * (point1.x)
                + 3 * oneMinusT * t * t * (point2.x)
                + t * t * t * (point3.x);

        point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                + 3 * oneMinusT * oneMinusT * t * (point1.y)
                + 3 * oneMinusT * t * t * (point2.y)
                + t * t * t * (point3.y);
        return point;
    }

    /**
     * 设置画笔
     */
    private void setPaint() {
        this.mPaint = new Paint();
        // 打开抗锯齿
        this.mPaint.setAntiAlias(true);
        /*
         * 设置画笔样式为填充 Paint.Style.STROKE：描边 Paint.Style.FILL_AND_STROKE：描边并填充
         * Paint.Style.FILL：填充
         */
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(Color.RED);
    }
}
