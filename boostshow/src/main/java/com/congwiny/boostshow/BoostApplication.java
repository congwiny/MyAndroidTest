package com.congwiny.boostshow;

import android.app.Application;

/**
 * Created by congwiny on 2015/12/17.
 */
public class BoostApplication extends Application {
    private static BoostApplication sApp;
    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static BoostApplication getsApp() {
        return sApp;
    }
}
