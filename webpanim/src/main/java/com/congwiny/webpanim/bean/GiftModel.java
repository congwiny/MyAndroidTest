package com.congwiny.webpanim.bean;

import com.google.gson.annotations.SerializedName;

public class GiftModel {

    public static final int TYPE_STATIC = 1;
    public static final int TYPE_DYNAMIC = 2;

    @SerializedName("gift_id")
    private int giftId;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("effect")
    private String effect;
    @SerializedName("exp")
    private String exp;
    @SerializedName("gift_type")
    private int giftType;
    @SerializedName("effect_md5")
    private String effectMd5;
    @SerializedName("diamond")
    private int diamond;

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public String getEffectMd5() {
        return effectMd5;
    }

    public void setEffectMd5(String effectMd5) {
        this.effectMd5 = effectMd5;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    @Override
    public String toString() {
        return "GiftModel{" +
                "giftId=" + giftId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", effect='" + effect + '\'' +
                ", exp='" + exp + '\'' +
                ", giftType='" + giftType + '\'' +
                ", effectMd5='" + effectMd5 + '\'' +
                ", diamond=" + diamond +
                '}';
    }
}