package com.congwiny.customprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.congwiny.customprogress.progress.HorizontalProgressBarWithNumber;

public class MainActivity extends AppCompatActivity {
    private HorizontalProgressBarWithNumber progressBarWithNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        progressBarWithNumber = (HorizontalProgressBarWithNumber) findViewById(R.id.progress);

        progressBarWithNumber.setMax(100);
        progressBarWithNumber.setProgress(50);
    }
}
