package com.congwiny.boostshow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;


import com.congwiny.boostshow.view.BoostEffectLayout;

public class MainActivity extends AppCompatActivity {

    private BoostEffectLayout mBoostRocketLayout;

    private WindowManager mWinManger;
    private WindowManager.LayoutParams mWinParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       // getWinManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        mBoostRocketLayout = (BoostEffectLayout) findViewById(R.id.boost_rocket_view);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mBoostRocketLayout.startBoostEffect();
    }

    public void performAnim(View v) {
        //  mBoostRocketLayout.launcherRocket();
        //  mBoostRocketLayout.rotateFinish();
        mBoostRocketLayout.startBoostEffect();
    }

    private void getWinManager() {
        mWinManger = (WindowManager) BoostApplication.getsApp()
                .getSystemService(Context.WINDOW_SERVICE);
        mWinParams = new WindowManager.LayoutParams();
        mWinParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mWinParams.format = PixelFormat.TRANSPARENT;
        mWinParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWinParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置黑暗度
        mWinParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        mWinParams.dimAmount = 0.4f;
    }
}
