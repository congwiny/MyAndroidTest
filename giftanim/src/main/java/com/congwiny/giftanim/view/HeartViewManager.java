package com.congwiny.giftanim.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class HeartViewManager {

    private Paint mPaint;
    // 火花喷射距离
    private float mDistance = 0;

    // 当前喷射距离
    private float mCurDistance = 0;

    // 每帧速度
    private static final float PER_SPEED_SEC = 1.0F;

    // 随机数
    private Random mRandom = new Random();

    // 火花的起始点，终点，塞贝儿曲线拐点1，塞贝儿曲线拐点2
    private Point start, end, c1, c2;

    // 是否是激活状态
    public boolean isActive = false;

    public HeartViewManager() {
        // 初始化画笔
        setSparkPaint();
    }

    public int[] drawSpark(Canvas canvas, int[] store) {

        this.mCurDistance = store[0];
        this.mDistance = store[1];

        // 初始化火花
        if (mCurDistance == mDistance) {
            mDistance = getRandom(HeartView.Y, mRandom.nextInt(15)) + 1;
            mCurDistance = 0;

            start = new Point(HeartView.X / 2, HeartView.Y);
            end = new Point(HeartView.X, 0);
//            c1 = getRandomPoint(start.x, start.y, mRandom.nextInt(HeartView.Y / 16));
//            c2 = getRandomPoint(end.x, end.y, mRandom.nextInt(HeartView.Y / 16));
            c1=new Point(mRandom.nextInt(HeartView.X/2),mRandom.nextInt(HeartView.Y/2));
            c2=new Point(mRandom.nextInt(HeartView.X/2),mRandom.nextInt(HeartView.Y/4));

        }
        // 恢复火花路径
        else {
            start.set(store[2], store[3]);
            end.set(store[4], store[5]);
            c1.set(store[6], store[7]);
            c2.set(store[8], store[9]);
        }

        // 更新火花路径
        updateSparkPath();
        // 计算塞贝儿曲线的当前点
        Point bezierPoint = CalculateBezierPoint(mCurDistance / mDistance, start, c1, c2, end);
        // 设置随机颜色
        mPaint.setColor(Color.argb(255, mRandom.nextInt(128) + 128, mRandom.nextInt(128) + 128, mRandom.nextInt(128) + 128));
        // 画花火
        canvas.drawCircle(bezierPoint.x, bezierPoint.y, 10, mPaint);


        // 重置火花状态
        if (mCurDistance == mDistance) {
            store[0] = 0;
            store[1] = 0;
        }
        // 保持花火的状态
        else {
            store[0] = (int) mCurDistance;
            store[1] = (int) mDistance;
            store[2] = (int) start.x;
            store[3] = (int) start.y;
            store[4] = (int) end.x;
            store[5] = (int) end.y;
            store[6] = (int) c1.x;
            store[7] = (int) c1.y;
            store[8] = (int) c2.x;
            store[9] = (int) c2.y;
        }

        return store;
    }

    /**
     * 更新火花路径
     */
    private void updateSparkPath() {
        mCurDistance += PER_SPEED_SEC;
        // 前半段
        if (mCurDistance < (mDistance / 2) && (mCurDistance != 0)) {
        }
        // 后半段
        else if (mCurDistance > (mDistance / 2) && (mCurDistance < mDistance)) {
        }
        // 完成
        else if (mCurDistance >= mDistance) {
            mCurDistance = 0;
            mDistance = 0;
        }
    }

    /**
     * 根据基准点获取指定范围为半径的随机点
     */
    private Point getRandomPoint(int baseX, int baseY, int r) {
        int x = mRandom.nextInt(r);
        int y = (int) Math.sqrt(r * r - x * x);

        x = baseX + getRandomPNValue(x);
        y = baseY + getRandomPNValue(y);

        return new Point(x, y);
    }

    /**
     * 根据range范围，和chance几率。返回一个随机值
     */
    private int getRandom(int range, int chance) {
        int num = 0;
        switch (chance) {
            case 0:
                num = mRandom.nextInt(range);
                break;
            default:
                num = mRandom.nextInt(range / 4);
                break;
        }

        return num;
    }

    /**
     * 获取随机正负数
     */
    private int getRandomPNValue(int value) {
        return mRandom.nextBoolean() ? value : 0 - value;
    }

    /**
     * 计算塞贝儿曲线
     *
     * @param t  时间，范围0-1
     * @param s  起始点
     * @param c1 拐点1
     * @param c2 拐点2
     * @param e  终点
     * @return 塞贝儿曲线在当前时间下的点
     */
    private Point CalculateBezierPoint(float t, Point s, Point c1, Point c2, Point e) {
        float u = 1 - t;
        float tt = t * t;
        float uu = u * u;
        float uuu = uu * u;
        float ttt = tt * t;

        Point p = new Point((int) (s.x * uuu), (int) (s.y * uuu));
        p.x += 3 * uu * t * c1.x;
        p.y += 3 * uu * t * c1.y;
        p.x += 3 * u * tt * c2.x;
        p.y += 3 * u * tt * c2.y;
        p.x += ttt * e.x;
        p.y += ttt * e.y;

        return p;
    }

    /**
     * 设置画笔
     */
    private void setSparkPaint() {
        this.mPaint = new Paint();
        // 打开抗锯齿
        this.mPaint.setAntiAlias(true);
        /*
         * 设置画笔样式为填充 Paint.Style.STROKE：描边 Paint.Style.FILL_AND_STROKE：描边并填充
         * Paint.Style.FILL：填充
         */
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }
}
