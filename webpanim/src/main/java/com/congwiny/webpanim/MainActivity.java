package com.congwiny.webpanim;

import android.animation.AnimatorSet;
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
import android.widget.FrameLayout;

import com.congwiny.webpanim.bean.anim.AnimationEffect;
import com.congwiny.webpanim.newbean.GiftAnimEffect;
import com.congwiny.webpanim.newview.GiftAnimView;
import com.congwiny.webpanim.utils.FileUtils;
import com.congwiny.webpanim.utils.GiftAnimParser;
import com.congwiny.webpanim.view.GiftAnimationView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SimpleDraweeView draweeView;
    private GiftAnimationView mGiftAnimContainer;
    private GiftAnimView mGiftAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
       // loadLocalImage();
        //loadWebpImage();
        //goAnim();

       startParseAnim();
    }

    private void startParseAnim() {
        String animConfig = FileUtils.readFileText("/sdcard/sololive/effect/ship3/animation.json");
        try{
            GiftAnimEffect giftEffect = new Gson().fromJson(animConfig, GiftAnimEffect.class);
            AnimatorSet animatorSet = GiftAnimParser.parseAnim(giftEffect, mGiftAnimView);
            animatorSet.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void goAnim() {
        String animConfig = FileUtils.readFileText("/sdcard/sololive/effect/ship3/android_animation.json");
        try{
            AnimationEffect effect = new Gson().fromJson(animConfig,AnimationEffect.class);
            mGiftAnimContainer.prepareAnim(effect);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadLocalImage() {
        //坑比，忘了文件的读取权限了
        Uri imageUri= Uri.fromFile(new File("/sdcard/sololive/effect/kiss3/kiss.jpg"));// For files on device
        draweeView.setImageURI(imageUri);

    }

    private void loadWebpImage(SimpleDraweeView draweeView){
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
        mGiftAnimContainer = (GiftAnimationView) findViewById(R.id.gift_anim_container);

        mGiftAnimView = (GiftAnimView) findViewById(R.id.gift_anim_view);
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
