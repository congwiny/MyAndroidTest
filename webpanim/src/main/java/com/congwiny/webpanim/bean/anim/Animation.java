
package com.congwiny.webpanim.bean.anim;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Animation {

    @SerializedName("startPoint")
    @Expose
    private StartPoint startPoint;
    @SerializedName("endPoint")
    @Expose
    private EndPoint endPoint;
    @SerializedName("duration")
    @Expose
    private Integer duration;

    /**
     * 
     * @return
     *     The startPoint
     */
    public StartPoint getStartPoint() {
        return startPoint;
    }

    /**
     * 
     * @param startPoint
     *     The startPoint
     */
    public void setStartPoint(StartPoint startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * 
     * @return
     *     The endPoint
     */
    public EndPoint getEndPoint() {
        return endPoint;
    }

    /**
     * 
     * @param endPoint
     *     The endPoint
     */
    public void setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
