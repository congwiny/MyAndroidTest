package com.congwiny.webpanim;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
       // loadLocalImage();
        loadWebpImage();
    }

    private void loadLocalImage() {
        //坑比，忘了文件的读取权限了
        Uri imageUri= Uri.fromFile(new File("/sdcard/sololive/effect/kiss3/kiss.jpg"));// For files on device
        draweeView.setImageURI(imageUri);

    }

    private void loadWebpImage(){
        Uri imageUri= Uri.fromFile(new File("/sdcard/sololive/effect/kiss3/kiss.webp"));
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUri)
                .setAutoPlayAnimations(true).build();
        draweeView.setController(controller);
    }

    private void initView() {
        //Uri uri = Uri.parse("http://img2.hao123.com/data/1_4c873d3785714bc7cbd6a31e8140c13c_0");
        draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
       // draweeView.setImageURI(uri);
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
}
