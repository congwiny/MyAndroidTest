package com.congwiny.camerahelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.congwiny.camerahelper.live.LiveStartResponse;
import com.google.gson.Gson;
import com.solo.rtmplive.CameraHelper;
import com.solo.rtmplive.ConstConfig;
import com.solo.rtmplive.MediaLiveHelper;
import com.solo.rtmplive.MediaPusherCallBack;
import com.solo.rtmplive.ParamException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView m_SurfaceView = null;
    private SurfaceHolder m_SurfaceHolder = null;
    private MediaLiveHelper m_MediaLiveHelper = null;
    private CameraHelper m_CameraHelper = null;
    private String liveJson = "{\"live\":{\"status\":2,\"room_id\":108485,\"user\":{\"avatar_url\":\"http://s3-us-west-2.amazonaws.com/solomedia/upload/a6dd151467050996000a14ad4b33e188.jpg\",\"user_id\":98,\"description\":\"\\u516b\\u5927\\u8c6a\\u4fe0\\u5317\\u4e8c\\u74b0\\u5927\\u5bb6\\u90fd\\u597d\\u767c\\u54c8\\u5c01ID\\u4f60\\u5230\\u54ea\\u53ef\\u60dc\\u80fd\\u6253\\u597d\\u4f4e\\u7d1a\\u90a3\\u4e9b\\u5c31\\u662f\\u89ba\\u5f97\\u5e7e\\u9ede\\u80fd\\u5230\\u5c31\\u6253\\u6297\\u64ca\\u6253\\u4f60\\u6253\\u4f60\\u5927\\u5e7e\\u4f60\\u4f11\\u5047\\u90fd\\u89ba\\u5f97\\u5c31\\u5230\\u5bb6\\u59d0\\u592b\\u597d\\u5c0d\\u5427\",\"level\":null,\"gender\":1,\"nickname\":\"Chunjian Ye\"},\"online_count\":0,\"title\":\"Solo around the world\",\"live_id\":108715,\"play_urls\":{\"hls\":\"http://cdn-hls.sololive.com/live/108715.m3u8\",\"rtmp\":\"rtmp://192.168.1.22:1935/live/108715\",\"flv\":\"http://cdn-flv.sololive.com/live/108715.flv\"},\"publish_url\":\"rtmp://push.sololive.com/live/108715\",\"share_url\":\"\",\"location\":\"\"},\"code\":1,\"message\":\"OK\"}";
    private LiveStartResponse liveStartResponse = new Gson().fromJson(liveJson, LiveStartResponse.class);
    private int m_enableAudio = 1;
    private int m_enableVideo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        m_SurfaceView = (SurfaceView) findViewById(R.id.record_view);
        setRecordParameter();
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private void setRecordParameter() {
        // Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        m_SurfaceHolder = m_SurfaceView.getHolder();
        m_SurfaceHolder.addCallback(this);
        m_SurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        //create media live object
        m_MediaLiveHelper = new MediaLiveHelper(this, m_SurfaceView);
        //m_CameraHelper = new CameraHelper(this);
        //initialize object
        m_MediaLiveHelper.initMeidaLiveHelper(new MediaPusherCallBack() {

            @Override
            public void onDisconnect() {
                if (m_MediaLiveHelper != null) {
                    m_MediaLiveHelper.deinitMeidaLiveHelper();
                }
            }

            @Override
            public void onConnecting() {
                //  TODO Auto-generated method stub
                //Log.e(TAG, "onConnecting");
            }

            @Override
            public void onConnected() {
                // TODO Auto-generated method stub
                //Log.e(TAG, "onConnecting");
            }
        });
        //set the camera view position and resolution ratio, before start preview camera

        //set the video resolution ratio as same as the camera setting
        try {
            m_MediaLiveHelper.setVideoOption(ConstConfig.VIDEO_ENCODING_HEIGHT_480);
            m_MediaLiveHelper.setCameraView(m_SurfaceHolder);
            m_MediaLiveHelper.setAudioOption(44100, 1);
//            m_MediaLiveHelper.setServerUrl(mLiveModel.getPublishUrl());
        } catch (ParamException e) {
            e.printStackTrace();
        }
        //set the audio option(use default), before start live
        //set the server url before connect to server
        //Toast.makeText(this, mLiveModel.getPublishUrl(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (m_MediaLiveHelper != null) {
            try {
                m_MediaLiveHelper.startPreviewCamera();
            } catch (ParamException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (m_SurfaceHolder != null) {
            m_SurfaceHolder.removeCallback(this);
        }
        stopRecord();
    }

    /**
     * 停止录制
     */
    private void stopRecord() {
        if (m_MediaLiveHelper != null) {
            m_MediaLiveHelper.stopPreviewCamera();
        }
    }
}
