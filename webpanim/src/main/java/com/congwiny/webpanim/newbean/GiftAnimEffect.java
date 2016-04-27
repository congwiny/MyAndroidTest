
package com.congwiny.webpanim.newbean;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GiftAnimEffect {

    @SerializedName("duration")
    @Expose
    private float duration;
    @SerializedName("animation_list")
    @Expose
    private List<AnimItem> animItem = new ArrayList<AnimItem>();

    /**
     * 
     * @return
     *     The duration
     */
    public float getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }


    public List<AnimItem> getAnimItem() {
        return animItem;
    }

    public void setAnimItem(List<AnimItem> animItem) {
        this.animItem = animItem;
    }

}
