package com.congwiny.camerahelper.live;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayURLModel implements Serializable {

    @SerializedName("rtmp")
    private String rtmpUrl;
    @SerializedName("hls")
    private String hlsUrl;
    @SerializedName("flv")
    private String flvUrl;

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    public String getFlvUrl() {
        return flvUrl;
    }

    public void setFlvUrl(String flvUrl) {
        this.flvUrl = flvUrl;
    }

    @Override
    public String toString() {
        return "PlayURLModel{" +
                "rtmpUrl='" + rtmpUrl + '\'' +
                ", hlsUrl='" + hlsUrl + '\'' +
                ", flvUrl='" + flvUrl + '\'' +
                '}';
    }
}