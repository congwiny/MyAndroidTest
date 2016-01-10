package com.congwiny.boostshow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.congwiny.boostshow.R;
import com.congwiny.boostshow.utils.FormatUtils;


/**
 * Created by congwiny on 2015/12/16.
 */
public class CircleProgressView extends View {

    private  final String TAG = CircleProgressView.class.getSimpleName();
    private Paint mProgressPaint;
    private Paint mProgressBgPaint;

    //borderStrokeWidth为progressPaint画出来的粗细，bgBorderStrokeWidth为progressBgPaint画出来的粗细
    private float mStrokeWidth;

    private final RectF mBounds = new RectF();

    private   final int ENTIRE_ANGLE = 360;
    private   final int START_ANGLE = 270;
    //释放的进度
    private int mFreeProgress = 0;
    //回弹的进度
    private int mBounceProgress = 0;


    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mStrokeWidth = 15;

        initPaint(context);
    }

    private void initPaint(Context context) {
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setStrokeWidth(mStrokeWidth);
        mProgressPaint.setColor(Color.WHITE);

        mProgressBgPaint = new Paint();
        mProgressBgPaint.setAntiAlias(true);
        mProgressBgPaint.setStyle(Paint.Style.STROKE);
        mProgressBgPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressBgPaint.setStrokeWidth(mStrokeWidth);
        mProgressBgPaint.setColor(context.getResources().getColor(
                R.color.boost_circle_progress_bg));


    }

    /**
     * measure之后会调用一边这个方法
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "---onSizeChanged");

        /**
         * 计算画布绘制圆弧填入的 top left bottom right 值,
         * 这里注意给的值要在圆弧的一半位置, 绘制的时候参数是从中间开始绘制
         */
        mBounds.left = mStrokeWidth / 2f + .5f;
        mBounds.right = w - mStrokeWidth / 2f - .5f;
        mBounds.top = mStrokeWidth / 2f + .5f;
        mBounds.bottom = h - mStrokeWidth / 2f - .5f;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //从270度开始，旋转360度
        canvas.drawArc(mBounds, START_ANGLE, ENTIRE_ANGLE, false, mProgressBgPaint);
        canvas.drawArc(mBounds, START_ANGLE, ENTIRE_ANGLE - mFreeProgress + mBounceProgress, false, mProgressPaint);
    }


    /**
     * @param progressStr
     */
    public void setFreeProgress(String progressStr) {
        this.mFreeProgress = FormatUtils.getIntPercent(progressStr);
        invalidate();
    }

    /**
     * @param progress
     */
    public void setFreeProgress(int progress) {
        this.mFreeProgress = progress;
        invalidate();
    }

    //回转
    public void setBounceProgress(int progress) {
        this.mBounceProgress = progress;
        invalidate();
    }

}
