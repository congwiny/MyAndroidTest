package com.congwiny.customprogress.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.congwiny.customprogress.R;


/**
 * Created by Wangpeng on 2016/5/26.
 */
public class HorizontalProgressBarWithNumber extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 12;
    private static final int DEFAULT_TEXT_COLOR = 0xFF2493E7;
    private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0xFFECEAEA;
    private static final int DEFAULT_COLOR_REACHED_COLOR = 0xFF2493E7;
    private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 6;
    private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 6;
    private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

    /**
     * painter of all drawing things
     */
    protected Paint mPaint = new Paint();

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public int getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getmReachedBarColor() {
        return mReachedBarColor;
    }

    public void setmReachedBarColor(int mReachedBarColor) {
        this.mReachedBarColor = mReachedBarColor;
    }

    public int getmUnReachedBarColor() {
        return mUnReachedBarColor;
    }

    public void setmUnReachedBarColor(int mUnReachedBarColor) {
        this.mUnReachedBarColor = mUnReachedBarColor;
    }

    public void setmUnReachedProgressBarHeight(int mUnReachedProgressBarHeight) {
        this.mUnReachedProgressBarHeight = mUnReachedProgressBarHeight;
    }

    public int getmUnReachedProgressBarHeight() {
        return mUnReachedProgressBarHeight;
    }

    public void setmReachedProgressBarHeight(int mReachedProgressBarHeight) {
        this.mReachedProgressBarHeight = mReachedProgressBarHeight;
    }

    public int getmReachedProgressBarHeight() {
        return mReachedProgressBarHeight;
    }

    /**
     * color of progress number
     */
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    /**
     * size of text (sp)
     */
    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);

    /**
     * offset of draw progress
     */
    protected int mTextOffset = dp2px(DEFAULT_SIZE_TEXT_OFFSET);

    /**
     * height of reached progress bar
     */
    protected int mReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

    /**
     * color of reached bar
     */
    protected int mReachedBarColor = DEFAULT_COLOR_REACHED_COLOR;
    /**
     * color of unreached bar
     */
    protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;
    /**
     * height of unreached progress bar
     */
    protected int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);
    /**
     * view width except padding
     */
    protected int mRealWidth;

    protected boolean mIfDrawText = true;

    protected static final int VISIBLE = 0;

    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(attrs);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
//                mPaint.clearShadowLayer();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);

        mRealWidth = getMeasuredWidth() - getPaddingRight() - getPaddingLeft();
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            float textHeight = (mPaint.descent() - mPaint.ascent());
            result = (int) (getPaddingTop() + getPaddingBottom() + Math.max(Math.max(mReachedProgressBarHeight,
                    mUnReachedProgressBarHeight), Math.abs(textHeight)));
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * get the styled attributes
     *
     * @param attrs
     */
    private void obtainStyledAttributes(AttributeSet attrs) {
        // init values from custom attributes
        final TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable
                .HorizontalProgressBarWithNumber);

        mTextColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_text_color,
                DEFAULT_TEXT_COLOR);
        mTextSize = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_size,
                mTextSize);

        mReachedBarColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_reached_color,
                mReachedBarColor);
        mUnReachedBarColor = attributes.getColor(R.styleable
                .HorizontalProgressBarWithNumber_progress_unreached_color, mUnReachedBarColor);
        mReachedProgressBarHeight = (int) attributes.getDimension(R.styleable
                .HorizontalProgressBarWithNumber_progress_reached_bar_height, mReachedProgressBarHeight);
        mUnReachedProgressBarHeight = (int) attributes.getDimension(R.styleable
                .HorizontalProgressBarWithNumber_progress_unreached_bar_height, mUnReachedProgressBarHeight);
        mTextOffset = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_offset,
                mTextOffset);

        int textVisible = attributes.getInt(R.styleable.HorizontalProgressBarWithNumber_progress_text_visibility,
                VISIBLE);
        if (textVisible != VISIBLE) {
            mIfDrawText = false;
        }
        attributes.recycle();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        boolean noNeedBg = false;
        float radio = getProgress() * 1.f / getMax();
        float progressPosX = (int) (mRealWidth * radio);
        String text = String.format("%d", getProgress() * 100 / getMax()) + "%";
        // mPaint.getTextBounds(text, 0, text.length(), mTextBound);

        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

        // 加载图片并获取宽高
        BitmapDrawable drawable = (BitmapDrawable) (getResources().getDrawable(R.drawable.img_product_detail_monkey));
        Bitmap bitmap = drawable.getBitmap();

        // 图片的宽高
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();

        float totalWidth = textWidth+width;


        /*if (progressPosX + textWidth > mRealWidth) {
            progressPosX = mRealWidth - textWidth;
            noNeedBg = true;
        }*/

        // draw reached bar
        float endX = progressPosX + textWidth;
        if (endX > 0) {
            mPaint.setColor(mReachedBarColor);
            mPaint.setStrokeWidth(mReachedProgressBarHeight);
            canvas.drawLine(0, 0, progressPosX, 0, mPaint);
        }
        // draw progress bar
        // measure text bound

        float textStart = progressPosX - textWidth;
        float picStart = progressPosX;
        if(progressPosX < totalWidth){
            textStart=0;
            picStart = textWidth;
        }

        if(mRealWidth-progressPosX<totalWidth){
            textStart = mRealWidth-totalWidth;
            picStart = mRealWidth-width;
        }

        if (mIfDrawText) {
            mPaint.setColor(mTextColor);
            canvas.drawText(text, textStart, textHeight, mPaint);
        }

        canvas.drawBitmap(bitmap, picStart, -height, mPaint);

        // draw unreached bar
        if (!noNeedBg) {
            mPaint.setColor(mUnReachedBarColor);
            mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
            canvas.drawLine(progressPosX, 0, mRealWidth, 0, mPaint);
        }
        canvas.restore();
    }


    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());

    }

}
