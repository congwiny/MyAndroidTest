
package com.congwiny.webpanim.newbean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimItem {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("scaleType")
    @Expose
    private int scaleType;
    @SerializedName("repeat")
    @Expose
    private int repeat;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("size")
    @Expose
    private Size size;
    @SerializedName("animations")
    @Expose
    private List<AnimFrame> animFrameSet = new ArrayList<AnimFrame>();

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The scaleType
     */
    public int getScaleType() {
        return scaleType;
    }

    /**
     * @param scaleType The scaleType
     */
    public void setScaleType(int scaleType) {
        this.scaleType = scaleType;
    }

    /**
     * @return The repeat
     */
    public int getRepeat() {
        return repeat;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * @param repeat The repeat
     */
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    /**
     * @return The size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    public List<AnimFrame> getAnimFrameSet() {
        return animFrameSet;
    }

    public void setAnimFrameSet(List<AnimFrame> animFrameSet) {
        this.animFrameSet = animFrameSet;
    }
}
