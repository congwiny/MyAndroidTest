package com.congwiny.webpanim.bean;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Geek4IT on 2/24/16.
 */
public class GiftChatModel extends BaseChatModel {


    public GiftChatModel() {
        type = TYPE_GIFT;
    }

    @SerializedName("gift")
    private GiftModel gift;

    @SerializedName("sequence")
    private int sequence;

    public GiftModel getGift() {
        return gift;
    }

    public void setGift(GiftModel gift) {
        this.gift = gift;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }



    @Override
    public String toString() {
        return "GiftChatModel [type = " + type + '\'' +
                ", gift = " + gift.toString() + "]";
    }


}
