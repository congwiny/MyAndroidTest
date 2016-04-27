
package com.congwiny.webpanim.bean.anim;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimationList {

    @SerializedName("animationName")
    @Expose
    private String animationName;
    @SerializedName("animations")
    @Expose
    private List<Animation> animations = new ArrayList<Animation>();
    @SerializedName("animationDuration")
    @Expose
    private Double animationDuration;
    @SerializedName("animationSize")
    @Expose
    private AnimationSize animationSize;

    /**
     * 
     * @return
     *     The animationName
     */
    public String getAnimationName() {
        return animationName;
    }

    /**
     * 
     * @param animationName
     *     The animationName
     */
    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }

    /**
     * 
     * @return
     *     The animations
     */
    public List<Animation> getAnimations() {
        return animations;
    }

    /**
     * 
     * @param animations
     *     The animations
     */
    public void setAnimations(List<Animation> animations) {
        this.animations = animations;
    }

    /**
     * 
     * @return
     *     The animationDuration
     */
    public Double getAnimationDuration() {
        return animationDuration;
    }

    /**
     * 
     * @param animationDuration
     *     The animationDuration
     */
    public void setAnimationDuration(Double animationDuration) {
        this.animationDuration = animationDuration;
    }

    /**
     * 
     * @return
     *     The animationSize
     */
    public AnimationSize getAnimationSize() {
        return animationSize;
    }

    /**
     * 
     * @param animationSize
     *     The animationSize
     */
    public void setAnimationSize(AnimationSize animationSize) {
        this.animationSize = animationSize;
    }

}
