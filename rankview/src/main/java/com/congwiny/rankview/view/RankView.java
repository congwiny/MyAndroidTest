package com.congwiny.rankview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by congwiny on 2016/5/19.
 */
public class RankView extends FrameLayout{
    public RankView(Context context) {
        this(context,null);
    }

    public RankView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
