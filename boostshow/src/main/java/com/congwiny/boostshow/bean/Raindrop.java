package com.congwiny.boostshow.bean;


import android.graphics.PointF;

/**
 * Created by congwiny on 2015/12/17.
 */
public class Raindrop {
    public PointF coordinate;
    public float dropIncrement;

    public Raindrop(float x, float y, float dropIncrement) {
        coordinate = new PointF(x, y);
        this.dropIncrement = dropIncrement;
    }

    public void dropRain(float x, float y) {
        coordinate.x = x;
        coordinate.y = y;
    }
}

