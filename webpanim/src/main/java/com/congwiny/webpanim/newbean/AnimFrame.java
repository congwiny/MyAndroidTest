
package com.congwiny.webpanim.newbean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimFrame {

    @SerializedName("duration")
    @Expose
    private float duration;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("end")
    @Expose
    private End end;

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

    /**
     * 
     * @return
     *     The start
     */
    public Start getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    public void setStart(Start start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The end
     */
    public End getEnd() {
        return end;
    }

    /**
     * 
     * @param end
     *     The end
     */
    public void setEnd(End end) {
        this.end = end;
    }

}
