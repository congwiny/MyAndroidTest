package com.congwiny.rankview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.congwiny.rankview.R;

/**
 * Created by congwiny on 2016/5/19.
 */
public class MarkedImageView extends CircleImageView {

    private boolean mShouldMark;
    private Drawable mMarkedDrawable;

    public MarkedImageView(Context context) {
        this(context, null);
    }

    public MarkedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarkedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMarkedDrawable();
    }

    private void initMarkedDrawable() {
        mMarkedDrawable = getResources().getDrawable(R.drawable.official_account);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShouldMark) {
            mMarkedDrawable.setBounds(getWidth() * 2 / 3, getHeight() * 2 / 3, getWidth(), getHeight());
            mMarkedDrawable.draw(canvas);
        }
    }

    public void showMark(boolean isMark) {
        mShouldMark = isMark;
        invalidate();
    }
}
