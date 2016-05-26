package com.congwiny.camerahelper.live;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LiveModel implements Serializable {

    public static final int STATUS_OFFLINE = 0;
    public static final int STATUS_ONLINE = 1;

    @SerializedName("live_id")
    private int liveId;

    @SerializedName("room_id")
    private int roomId;


    @SerializedName("title")
    private String title;

    @SerializedName("city")
    private String location;

    @SerializedName("status")
    private int status;

    @SerializedName("publish_url")
    private String publishUrl;

    @SerializedName("play_urls")
    private PlayURLModel playUrls;

    @SerializedName("share_url")
    private String shareUrl;

    @SerializedName("online_count")
    private int onlineCount;

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPublishUrl() {
        return publishUrl;
    }

    public void setPublishUrl(String publishUrl) {
        this.publishUrl = publishUrl;
    }

    public PlayURLModel getPlayUrls() {
        return playUrls;
    }

    public void setPlayUrls(PlayURLModel playUrls) {
        this.playUrls = playUrls;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getOnlineUsersCount() {
        return onlineCount;
    }

    public void setOnlineUsersCount(int onlineUsers) {
        this.onlineCount = onlineUsers;
    }

    @Override
    public String toString() {
        return "LiveModel{" +
                "liveId=" + liveId +
                ", roomId=" + roomId +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", publishUrl='" + publishUrl + '\'' +
                ", playUrls=" + playUrls +
                ", shareUrl='" + shareUrl + '\'' +
                ", onlineCount=" + onlineCount +
                '}';
    }
}