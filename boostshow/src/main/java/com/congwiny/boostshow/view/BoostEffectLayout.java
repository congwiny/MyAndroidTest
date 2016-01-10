package com.congwiny.boostshow.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.congwiny.boostshow.R;
import com.congwiny.boostshow.utils.SoloCleanUtils;

/**
 * Created by congwiny on 2015/12/16.
 */
public class BoostEffectLayout extends FrameLayout {
    private static final String TAG = BoostEffectLayout.class.getSimpleName();
    //进度圆圈
    private CircleProgressView mProgressView;

    private CircleRainyView mRainyView;
    private ImageView mRocketIv;
    private ImageView mFinishIv;
    private Context mContext;
    private ImageView mCloudIv;

    public BoostEffectLayout(Context context) {
        this(context, null);
    }

    public BoostEffectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mProgressView = (CircleProgressView) findViewById(R.id.progress_view);
        mRainyView = (CircleRainyView) findViewById(R.id.rainy_view);
        mRocketIv = (ImageView) findViewById(R.id.rocket_iv);
        mFinishIv = (ImageView) findViewById(R.id.finish_iv);
        mCloudIv = (ImageView) findViewById(R.id.cloud_iv);
    }


    public void startBoostEffect() {

        int runMemPercent = SoloCleanUtils.getRunMemPercent(mContext);

        int freePercent = SoloCleanUtils.getFreePercent(mContext);

        int usedMemPercent = runMemPercent - freePercent;

        Log.e(TAG, "runMemPercent=" + runMemPercent + ",freePercent=" + freePercent);
        //时间要定死了。。需要时间去加载广告
        progressAnim(100 - usedMemPercent, 1200, (100 - usedMemPercent) / 5);
    }


    private void progressAnim(int freeMemPercent, int duration, int bounceMemPercent) {

        int freeProgress = freeMemPercent * 360 / 100;

        int bounceProgress = bounceMemPercent*360/100;

        ValueAnimator progressValueAnimator = ValueAnimator.ofInt(0, freeProgress);
        progressValueAnimator.setInterpolator(new AccelerateInterpolator());
        progressValueAnimator.setDuration(duration);

        progressValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (Integer) animation.getAnimatedValue();
                mProgressView.setFreeProgress(progress);
            }
        });


        ValueAnimator bounceValueAnimator = ValueAnimator.ofInt(0, bounceProgress);
        bounceValueAnimator.setDuration(600);
      //  bounceValueAnimator.setInterpolator(new AccelerateInterpolator());

        bounceValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (Integer) animation.getAnimatedValue();
                mProgressView.setBounceProgress(progress);
            }
        });


        mCloudIv.setVisibility(VISIBLE);
        ObjectAnimator cloudAlphaAnimator = ObjectAnimator.ofFloat(mCloudIv, "alpha", 0.0f, 1.0f);
        cloudAlphaAnimator.setDuration(300);
        cloudAlphaAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mRainyView.startRain();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        Animator rocketShowAnimator = AnimatorInflater.loadAnimator(mContext, R.animator.anim_trans_rocket_show);
        rocketShowAnimator.setTarget(mRocketIv);


        Animator rocketLaunchAnimator = AnimatorInflater.loadAnimator(mContext, R.animator.anim_trans_rocket_launch);
        rocketLaunchAnimator.setTarget(mRocketIv);
        mRocketIv.setVisibility(VISIBLE);

        AnimatorSet cleanAnimSet = new AnimatorSet();
        cleanAnimSet.playTogether(progressValueAnimator, rocketShowAnimator, cloudAlphaAnimator);

        AnimatorSet bounceAnimSet = new AnimatorSet();
        bounceAnimSet.playTogether(bounceValueAnimator, rocketLaunchAnimator);

        bounceAnimSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                mFinishIv.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mFinishIv.getVisibility() == INVISIBLE) {
                            mFinishIv.setVisibility(VISIBLE);
                        }

                        mRainyView.setVisibility(INVISIBLE);
                        mCloudIv.setVisibility(INVISIBLE);

                    }
                }, 300);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        Animator finishAnim = AnimatorInflater.loadAnimator(mContext, R.animator.anim_rotate_finish);
        finishAnim.setTarget(mFinishIv);

        finishAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mRainyView.setVisibility(INVISIBLE);
                mCloudIv.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet completeAnimSet = new AnimatorSet();

        completeAnimSet.playSequentially(cleanAnimSet, bounceAnimSet, finishAnim);

        completeAnimSet.start();
    }
}
