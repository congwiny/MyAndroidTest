package com.congwiny.materialdesign;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private TextView mContentTv;
    private CoordinatorLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置了setSupportActionBar ，标题就粗来了
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        // App Logo
        //mToolbar.setLogo(R.mipmap.ic_launcher);

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_toolbar);

        initView();

    }

    private void initView() {
        mContentTv = (TextView) findViewById(R.id.tv_content);
        mContainer = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        //title=Material Design Demo,color=0
        // Title
        mToolbar.setTitle(title);
        // Sub Title
        mToolbar.setSubtitle("Sub title");
        //隐藏标题
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
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
      /*  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_edit:
                return true;
            case R.id.action_share:
                Snackbar snackBar = Snackbar.make(mContainer,"hello boy",Snackbar.LENGTH_SHORT);
                snackBar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackBar.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
