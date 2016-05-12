package com.congwiny.spanstringdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RoundedBackgroundSpan extends ReplacementSpan {

    private int cornerRadius = 0;
    private int backgroundColor = 0;
    private int textColor = 0;
    private int paddingLR = 0;

    public RoundedBackgroundSpan(Context context) {
        super();
        backgroundColor = context.getResources().getColor(R.color.colorAccent);
        textColor = context.getResources().getColor(R.color.colorPrimary);
        cornerRadius = context.getResources().getDimensionPixelSize(R.dimen.rounded_span_corner_radius);
        paddingLR = context.getResources().getDimensionPixelSize(R.dimen.rounded_span_paddingLR);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end) + 2 * paddingLR, bottom);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + paddingLR, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end)) + 2 * paddingLR;
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}