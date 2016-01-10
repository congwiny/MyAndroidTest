package com.congwiny.viewmeausure.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.congwiny.viewmeausure.R;

/**
 * Created by congwiny on 15/11/17.
 */
public class CustomView extends View {
    private static final String TAG = "CustomView";
    private static int measure = 0;
    private static int layout = 0;
    private static int draw = 0;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "CustomView onMeasure time=" + (++measure));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "CustomView onLayout time=" + (++layout));
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "CustomView onDraw time=" + (++draw));
        super.onDraw(canvas);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.img_meinv), 0, 0, new Paint());
    }
}
