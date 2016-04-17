package com.congwiny.giftanim.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.congwiny.giftanim.R;


public class StrokeTextView extends TextView {
    private TextView textView;

    public StrokeTextView(Context paramContext) {
        this(paramContext,null);
    }

    public StrokeTextView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet,0);
    }

    public StrokeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        this.textView = new TextView(paramContext, paramAttributeSet, paramInt);
        initTextView();
    }

    private void initTextView() {
        TextPaint localTextPaint = this.textView.getPaint();
        localTextPaint.setStrokeWidth(4.0F);
        localTextPaint.setStyle(Paint.Style.STROKE);
        this.textView.setTextColor(getResources().getColor(R.color.continue_gift_desc));
        this.textView.setGravity(getGravity());
    }

    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        this.textView.draw(paramCanvas);
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        this.textView.layout(paramInt1, paramInt2, paramInt3, paramInt4);
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        CharSequence localCharSequence = this.textView.getText();
        if ((localCharSequence == null) || (!localCharSequence.equals(getText()))) {
            this.textView.setText(getText());
            postInvalidate();
        }
        super.onMeasure(paramInt1, paramInt2);
        this.textView.measure(paramInt1, paramInt2);
    }

    public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
        super.setLayoutParams(paramLayoutParams);
        this.textView.setLayoutParams(paramLayoutParams);
    }

    public void setText(CharSequence paramCharSequence, BufferType paramBufferType) {
        super.setText(paramCharSequence, BufferType.NORMAL);
        if (this.textView != null)
            this.textView.setText(paramCharSequence);
    }

    public void setVisibility(int paramInt) {
        super.setVisibility(paramInt);
        this.textView.setVisibility(paramInt);
    }
}