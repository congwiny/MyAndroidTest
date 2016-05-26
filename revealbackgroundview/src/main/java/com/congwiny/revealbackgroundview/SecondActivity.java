package com.congwiny.revealbackgroundview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.congwiny.revealbackgroundview.view.RevealBackgroundView;

public class SecondActivity extends AppCompatActivity implements RevealBackgroundView.OnStateChangeListener{

    RevealBackgroundView mRevealBackgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  hideStatusBar();
        setContentView(R.layout.activity_second);

        initView();
        setupRevealBackground(savedInstanceState);
    }

    private void setupRevealBackground(Bundle savedInstanceState) {
        mRevealBackgroundView.setFillPaintColor(getResources().getColor(R.color.colorAccent));
        mRevealBackgroundView.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra("location");
            mRevealBackgroundView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mRevealBackgroundView.getViewTreeObserver().removeOnPreDrawListener(this);
                    mRevealBackgroundView.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            mRevealBackgroundView.setToFinishedFrame();
        }
    }

    private void initView() {
        mRevealBackgroundView = (RevealBackgroundView) findViewById(R.id.reveal_view);
    }

    @Override
    public void onStateChange(int state) {
        if (state == RevealBackgroundView.STATE_FINISHED) {
            mRevealBackgroundView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 隐藏状态栏
     */
    protected void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void showStatusBar(){
        getWindow().setFlags(~WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
