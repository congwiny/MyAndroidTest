package com.congwiny.webpanim.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SimpleUserModel implements Serializable {

    @SerializedName("user_id")
    protected int userId;

    @SerializedName("nickname")
    protected String nickname;

    @SerializedName("level")
    protected int level;

    @SerializedName("avatar_url")
    protected String avatarUrl;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "SimpleUserModel{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", level=" + level +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

}