package com.congwiny.viewmeausure;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by congwiny on 15/11/17.
 */
public class MeasureActivity extends Activity{
    private static final String TAG = "MeasureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        //Dalvik/2.1.0 (Linux; U; Android 5.1; Android SDK built for x86 Build/LKY45)
        String userAgent = System.getProperty("http.agent");
        Log.e(TAG,"user agent="+userAgent);
    }
}
