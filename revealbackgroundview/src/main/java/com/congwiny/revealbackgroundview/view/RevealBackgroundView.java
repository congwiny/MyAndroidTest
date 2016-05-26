package com.congwiny.revealbackgroundview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

public class RevealBackgroundView extends View {
    public static final int STATE_NOT_STARTED = 0;// 没有开始状态
    public static final int STATE_FILL_STARTED = 1;// 填充满状态
    public static final int STATE_FINISHED = 2;// 填充完成状态

    // 在动画开始的时候速率比较慢，后面持续增加
    private static final Interpolator INTERPOLATOR = new AccelerateInterpolator();
    private static final int FILL_TIME = 600;// 动画时间600毫秒

    private int state = STATE_NOT_STARTED;// 默认状态

    private Paint fillPaint;// 画笔
    private int currentRadius;// 半径
    ObjectAnimator revealAnimator;

    private int startLocationX;// 控件的x坐标值
    private int startLocationY;// 控件的Y坐标值

    private OnStateChangeListener onStateChangeListener;

    public RevealBackgroundView(Context context) {
        super(context);
        init();
    }

    public RevealBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RevealBackgroundView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.WHITE);
    }

    // 设置画笔的颜色
    public void setFillPaintColor(int color) {
        fillPaint.setColor(color);
    }

    //开启动画
    public void startFromLocation(int[] tapLocationOnScreen) {
        changeState(STATE_FILL_STARTED);

        startLocationX = tapLocationOnScreen[0];//传递过来view的x的RELATIVE_TO_SELF坐标值
        startLocationY = tapLocationOnScreen[1];//传递过来view的y的RELATIVE_TO_SELF坐标值
        // 动画标记为当前的半径currentRadius，值为RevealBackgroundView的0--width+height
        revealAnimator = ObjectAnimator.ofInt(this, "currentRadius", 0,
                getWidth() + getHeight()).setDuration(FILL_TIME);
        revealAnimator.setInterpolator(INTERPOLATOR);
        // 动画监听器
        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 在动画结束的时候调用该方法
                changeState(STATE_FINISHED);
            }
        });
        revealAnimator.start();
    }

    // 当回调为true时，调用该方法，重新绘制当前界面
    public void setToFinishedFrame() {
        changeState(STATE_FINISHED);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 在动画完成后直接画整个界面，不在让他继续扩散
        if (state == STATE_FINISHED) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), fillPaint);
        } else {
            // 绘制点击控件位置扩散的圆圈
            canvas.drawCircle(startLocationX, startLocationY, currentRadius,
                    fillPaint);
        }
    }

    // 判断当前的状态
    private void changeState(int state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        // 将当前状态不停的回调，回调时判断一下当前是否完成，是的话就显示底部布局
        if (onStateChangeListener != null) {
            onStateChangeListener.onStateChange(state);
        }
    }

    // 设置圆圈的半径时，重新调用onDraw，重新画圆
    public void setCurrentRadius(int radius) {
        this.currentRadius = radius;
        invalidate();
    }

    public void setOnStateChangeListener(
            OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    public interface OnStateChangeListener {
        void onStateChange(int state);
    }
}