package com.congwiny.giftanim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.congwiny.giftanim.view.ContinueGiftView;
import com.congwiny.giftanim.view.HeartView;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


public class MainActivity extends AppCompatActivity {
    int i = 2;
    private static final String DRAWABLE = "drawable://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ContinueGiftView giftView = (ContinueGiftView) findViewById(R.id.gift_view);
        Button btnStart = (Button) findViewById(R.id.btn_start);
        final ImageView imageView = (ImageView) findViewById(R.id.car_one_front_wheel);
        final ImageView imageView1 = (ImageView) findViewById(R.id.car_one_back_wheel);
        final RelativeLayout carOne = (RelativeLayout) findViewById(R.id.car_one);
        final HeartView heartView = (HeartView) findViewById(R.id.heart_view);
        giftView.showGiftViewOnce();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giftView.showGiftView();
                giftView.setIsContinue(true);
                giftView.setContinueTimes(i);
                i++;
                startCarOnAnimation(carOne);
            }
        });

        ImageLoader.getInstance().displayImage(DRAWABLE + R.mipmap.gift_common_wheel, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                ViewAnimator
                        .animate(imageView)
                        .rotationX(0, -10)
                        .rotationY(0f, 30.0f)
                        .duration(30)
                        .onStop(new AnimationListener.Stop() {
                            @Override
                            public void onStop() {
                                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, -360f);
                                objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                                objectAnimator.setRepeatMode(ValueAnimator.RESTART);
                                objectAnimator.setDuration(100);
                                objectAnimator.start();
                            }
                        })
                        .start();
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        ImageLoader.getInstance().displayImage(DRAWABLE + R.mipmap.gift_common_wheel, imageView1, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                ViewAnimator
                        .animate(imageView1)
                        .rotationX(0, -16)
                        .rotationY(0f, 40.0f)
                        .duration(30)
                        .onStop(new AnimationListener.Stop() {
                            @Override
                            public void onStop() {
                                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView1, "rotation", 0f, -360f);
                                objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                                objectAnimator.setRepeatMode(ValueAnimator.RESTART);
                                objectAnimator.setDuration(100);
                                objectAnimator.start();
                            }
                        })
                        .start();
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

    }

    private void startCarOnAnimation(final RelativeLayout carOne) {
        carOne.setVisibility(View.VISIBLE);
        final Animation carAnimation = AnimationUtils.loadAnimation(this, R.anim.car_one_show);
        carAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                carOne.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        carAnimation.setInterpolator(new DecelerateInterpolator());
        carOne.startAnimation(carAnimation);
    }
}
