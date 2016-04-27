package com.congwiny.webpanim;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by congwiny on 2016/4/24.
 */
public class FrescoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
