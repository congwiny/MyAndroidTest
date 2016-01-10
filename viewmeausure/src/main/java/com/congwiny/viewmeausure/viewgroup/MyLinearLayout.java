package com.congwiny.viewmeausure.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by congwiny on 15/11/17.
 */
public class MyLinearLayout extends LinearLayout{
    private static final String TAG = "MyLinearLayout";
    private static int measure = 0;
    private static int layout = 0;
    private static int draw = 0;
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "MyLinearLayout onMeasure time=" + (++measure));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         *     @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mOrientation == VERTICAL) {
        measureVertical(widthMeasureSpec, heightMeasureSpec);
        } else {
        measureHorizontal(widthMeasureSpec, heightMeasureSpec);
        }
        }
         */
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG, "MyLinearLayout onLayout time=" + (++layout));
        super.onLayout(changed, l, t, r, b);
    }
}
