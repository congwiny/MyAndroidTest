package com.congwiny.webpanim.bean;

import android.content.Context;
import android.text.SpannableString;

import com.google.gson.annotations.SerializedName;

/**
 * Created by congwiny on 2016/2/20.
 */
public class BaseChatModel {

    public static final int TYPE_SYS = 1;
    public static final int TYPE_PUB = 2;
    public static final int TYPE_LIKE = 3;
    public static final int TYPE_GIFT = 4;

    protected BaseChatModel() {
    }

    @SerializedName("room_id")
    protected int roomId;

    @SerializedName("type")
    protected int type;

    @SerializedName("user")
    protected SimpleUserModel user;

    protected SpannableString mSendableMessage;


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public SimpleUserModel getUser() {
        return user;
    }

    public void setUser(SimpleUserModel sender) {
        this.user = sender;
    }

    public  SpannableString getSendableString(Context context){
        return mSendableMessage;
    }

    @Override
    public String toString() {
        return "BaseModel [room_id = " + roomId + '\'' +
                ", type = " + type + '\'' +
                ", user = " + user.toString() + "]";
    }

}
