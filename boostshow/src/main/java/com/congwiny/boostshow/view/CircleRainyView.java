package com.congwiny.boostshow.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;


import com.congwiny.boostshow.R;
import com.congwiny.boostshow.bean.Raindrop;

import java.util.Random;

/**
 * Created by congwiny on 2015/12/16.
 */
public class CircleRainyView extends View {
    private static final String TAG = "CircleRainyView";
    private Paint mRainPaint;
    private final float mCircleStrokeWidth = 5f;
    private final int MAX_RAINDROP_COUNT = 30;
    private final int DROP_INCREMENT = 30;
    private final int INCREMENT_OFFSET = 30;

    private   final int ENTIRE_ANGLE = 360;
    private   final int START_ANGLE = 270;

    private final Random mRandom = new Random();
    private Paint mCircleBgPaint;

    private Bitmap mRainBmp;

    private Bitmap mMaskBmp;
    private final RectF mBounds = new RectF();

    public boolean isRain;

    //雨滴
    private Raindrop[] mRaindrops = new Raindrop[MAX_RAINDROP_COUNT];


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
            sendEmptyMessageDelayed(0, 100);

        }
    };

    public CircleRainyView(Context context) {
        this(context, null);
    }

    public CircleRainyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleRainyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mRainBmp = BitmapFactory.decodeResource(getResources(), R.drawable.raindrop_l2);

        mRainPaint = new Paint();
        mRainPaint.setAntiAlias(true);

        mCircleBgPaint = new Paint();
        mCircleBgPaint.setAntiAlias(true);
        mCircleBgPaint.setStyle(Paint.Style.STROKE);
        mCircleBgPaint.setStrokeCap(Paint.Cap.ROUND);
        mCircleBgPaint.setStrokeWidth(mCircleStrokeWidth);
        mCircleBgPaint.setColor(context.getResources().getColor(
                R.color.boost_circle_progress_bg));

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBounds.left = mCircleStrokeWidth / 2f + .5f;
        mBounds.right = w - mCircleStrokeWidth / 2f - .5f;
        mBounds.top = mCircleStrokeWidth / 2f + .5f;
        mBounds.bottom = h - mCircleStrokeWidth / 2f - .5f;


        mMaskBmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mMaskBmp.eraseColor(getResources().getColor(R.color.green_bg));
        mMaskBmp = createHollowCircleImage(mMaskBmp, getWidth(), PorterDuff.Mode.SRC_OUT);

        initRaindrops(w, h);
    }

    private void initRaindrops(int width, int height) {
        for (int i = 0; i < MAX_RAINDROP_COUNT; i++) {
            mRaindrops[i] = new Raindrop(mRandom.nextInt(width), mRandom.nextInt(height), mRandom.nextInt(DROP_INCREMENT) + INCREMENT_OFFSET);
        }
    }

    public void startRain() {
        isRain = true;
        handler.sendEmptyMessage(0);
    }

    public void stopRain() {

        isRain = false;
        handler.removeMessages(0);
        invalidate();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isRain) {

            for (int i = 0; i < MAX_RAINDROP_COUNT; i++) {
                if (mRaindrops[i].coordinate.y >= getHeight()) {
                    mRaindrops[i].dropRain(mRandom.nextInt(getWidth()), 0);
                } else {
                    mRaindrops[i].dropRain(mRaindrops[i].coordinate.x, mRaindrops[i].coordinate.y + mRaindrops[i].dropIncrement);
                }

                canvas.drawBitmap(mRainBmp, mRaindrops[i].coordinate.x, mRaindrops[i].coordinate.y, mRainPaint);
            }
        }

        canvas.drawBitmap(mMaskBmp, 0, 0, null);

        canvas.drawArc(mBounds, START_ANGLE, ENTIRE_ANGLE, false, mCircleBgPaint);
    }

    /**
     * 绘制空心圆图片
     *
     * @param source
     * @param minSize
     * @return
     */
    private Bitmap createHollowCircleImage(Bitmap source, int minSize, PorterDuff.Mode mode) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap target = Bitmap.createBitmap(minSize, minSize, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(minSize / 2, minSize / 2, minSize / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(mode));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }
}

