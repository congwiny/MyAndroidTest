package com.congwiny.camerahelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by congwiny on 2016/5/17.
 */
public class PrepareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
    }

    public void startLive(View view) {
        startActivity(MainActivity.getStartIntent(this));
    }
}
