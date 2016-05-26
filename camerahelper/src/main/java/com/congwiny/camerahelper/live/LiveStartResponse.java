package com.congwiny.camerahelper.live;

import com.google.gson.annotations.SerializedName;

public class LiveStartResponse extends BaseResponse {

    @SerializedName("live")
    private LiveModel liveModel;


    /**
     * @return The liveModel
     */
    public LiveModel getLiveModel() {
        return liveModel;
    }

    /**
     * @param liveModel The liveModel
     */
    public void setLiveModel(LiveModel liveModel) {
        this.liveModel = liveModel;
    }


    @Override
    public String toString() {
        return "{LiveStartResponse: " + liveModel.toString() + "}";
    }

}