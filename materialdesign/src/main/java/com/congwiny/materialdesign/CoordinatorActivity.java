package com.congwiny.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by congwiny on 2016/5/5.
 */
public class CoordinatorActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
    }
}
