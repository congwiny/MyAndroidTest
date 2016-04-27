
package com.congwiny.webpanim.newbean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Start {

    @SerializedName("x")
    @Expose
    private float x;
    @SerializedName("y")
    @Expose
    private float y;
    @SerializedName("scale")
    @Expose
    private float scale;

    /**
     * 
     * @return
     *     The x
     */
    public float getX() {
        return x;
    }

    /**
     * 
     * @param x
     *     The x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * 
     * @return
     *     The y
     */
    public float getY() {
        return y;
    }

    /**
     * 
     * @param y
     *     The y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * 
     * @return
     *     The scale
     */
    public float getScale() {
        return scale;
    }

    /**
     * 
     * @param scale
     *     The scale
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

}
