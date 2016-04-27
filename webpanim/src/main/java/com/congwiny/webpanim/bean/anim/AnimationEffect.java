
package com.congwiny.webpanim.bean.anim;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimationEffect {

    @SerializedName("animationList")
    @Expose
    private List<AnimationList> animationList = new ArrayList<AnimationList>();
    @SerializedName("animationBackgroundName")
    @Expose
    private String animationBackgroundName;
    @SerializedName("animationForegroundName")
    @Expose
    private String animationForegroundName;

    /**
     * 
     * @return
     *     The animationList
     */
    public List<AnimationList> getAnimationList() {
        return animationList;
    }

    /**
     * 
     * @param animationList
     *     The animationList
     */
    public void setAnimationList(List<AnimationList> animationList) {
        this.animationList = animationList;
    }

    /**
     * 
     * @return
     *     The animationBackgroundName
     */
    public String getAnimationBackgroundName() {
        return animationBackgroundName;
    }

    /**
     * 
     * @param animationBackgroundName
     *     The animationBackgroundName
     */
    public void setAnimationBackgroundName(String animationBackgroundName) {
        this.animationBackgroundName = animationBackgroundName;
    }

    /**
     * 
     * @return
     *     The animationForegroundName
     */
    public String getAnimationForegroundName() {
        return animationForegroundName;
    }

    /**
     * 
     * @param animationForegroundName
     *     The animationForegroundName
     */
    public void setAnimationForegroundName(String animationForegroundName) {
        this.animationForegroundName = animationForegroundName;
    }

}
