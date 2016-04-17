package com.congwiny.giftanim.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public abstract class CustomBaseViewRelative extends RelativeLayout {
    public Context x;
    public LayoutInflater y;

    public CustomBaseViewRelative(Context paramContext) {
        super(paramContext);
        this.x = paramContext;
        this.y = LayoutInflater.from(this.x);
        this.y.inflate(getLayoutId(), this, true);
        initView();
    }

    public CustomBaseViewRelative(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.x = paramContext;
        this.y = LayoutInflater.from(this.x);
        this.y.inflate(getLayoutId(), this, true);
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}