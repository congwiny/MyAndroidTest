package com.congwiny.rankview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.congwiny.rankview.view.MarkedImageView;
import com.facebook.ads.MediaView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MarkedImageView miv = (MarkedImageView) findViewById(R.id.mark_image);
        miv.showMark(true);

       MediaView ad = new MediaView(this);
    }
}
