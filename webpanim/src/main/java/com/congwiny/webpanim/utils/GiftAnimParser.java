package com.congwiny.webpanim.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.congwiny.webpanim.newbean.AnimFrame;
import com.congwiny.webpanim.newbean.AnimItem;
import com.congwiny.webpanim.newbean.GiftAnimEffect;
import com.congwiny.webpanim.newbean.Size;
import com.congwiny.webpanim.view.ViewUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by congwiny on 2016/4/26.
 */
public class GiftAnimParser {

    private static final String TAG = GiftAnimParser.class.getSimpleName();

    public static AnimatorSet parseAnim(GiftAnimEffect effect, final ViewGroup animContainer) {
        AnimatorSet rootAnimatorSet = new AnimatorSet();

        ArrayList<Animator> itemAnimList = new ArrayList<>();
        final ArrayList<SimpleDraweeView> mWebpImages = new ArrayList<>();
        //并行执行的动画
        for (AnimItem animItem : effect.getAnimItem()) {

            SimpleDraweeView animView = new SimpleDraweeView(animContainer.getContext());
            int scaleType = animItem.getScaleType();
            switch (scaleType) {
                case 1:
                   // animView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
                    break;
                case 2:
                    animView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
                    break;
            }


            Size size = animItem.getSize();
            int width = ViewUtils.sizeDp2px(animContainer.getResources(), size.getWidth());
            int height = ViewUtils.sizeDp2px(animContainer.getResources(), size.getHeight());

            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
            animContainer.addView(animView,lp);

            if (prepareBg(animView, animItem.getName())) {
                mWebpImages.add(animView);
            }

            AnimatorSet wholeFrameAnimSet = new AnimatorSet();
            ArrayList<Animator> frameAnimList = new ArrayList<>();
            //所有的帧动画
            for (AnimFrame animFrame : animItem.getAnimFrameSet()) {
                ObjectAnimator transAnimX = ObjectAnimator.ofFloat(animView, "translationX",
                        animFrame.getStart().getX() * ViewUtils.getScreenWidth(animView.getContext()),
                        animFrame.getEnd().getX() * ViewUtils.getScreenWidth(animView.getContext()));

                ObjectAnimator transAnimY = ObjectAnimator.ofFloat(animView, "translationY",
                        animFrame.getStart().getY() * ViewUtils.getScreenHeight(animView.getContext()),
                        animFrame.getEnd().getY() * ViewUtils.getScreenHeight(animView.getContext()));

                ObjectAnimator scaleAnimX = ObjectAnimator.ofFloat(animView,
                        "scaleX", animFrame.getStart().getScale(), animFrame.getEnd().getScale());
                ObjectAnimator scaleAnimY = ObjectAnimator.ofFloat(animView,
                        "scaleY", animFrame.getStart().getScale(), animFrame.getEnd().getScale());
                AnimatorSet singleFrameAnimSet = new AnimatorSet();
                singleFrameAnimSet.playTogether(transAnimX, transAnimY, scaleAnimX, scaleAnimY);
                singleFrameAnimSet.setDuration((long) (1000 * animFrame.getDuration()));
                frameAnimList.add(singleFrameAnimSet);
            }

            wholeFrameAnimSet.playSequentially(frameAnimList);
            itemAnimList.add(wholeFrameAnimSet);
        }
        rootAnimatorSet.playTogether(itemAnimList);
       // rootAnimatorSet.setDuration((long) (1000 * effect.getDuration()));
        rootAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG,"listener onAnimationStart"+mWebpImages.size());
                for (SimpleDraweeView webpImage : mWebpImages) {
                    Log.e(TAG,"lis simpledrawee="+webpImage.hashCode());
                    Animatable anim = webpImage.getController().getAnimatable();
                    if (anim != null) {
                        Log.e(TAG,"Animatable webp");
                        anim.start();
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //animContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return rootAnimatorSet;
    }

    public static boolean prepareBg(SimpleDraweeView bgImage, String bgFileName) {
        if (!TextUtils.isEmpty(bgFileName)) {
            File bgFile = new File("/sdcard/sololive/effect/ship3", bgFileName);
            Log.e(TAG,"bgFile="+bgFile.getAbsolutePath());
            if (bgFile.exists()) {
                if (FileUtils.isWebpFile(bgFileName)) {
                    Log.e(TAG, "isWebp File");
                    ImageRequest localImageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.fromFile(bgFile)).build();
                    Log.e(TAG,"simpledrawee="+bgImage.hashCode());
                    ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
                        @Override
                        public void onFinalImageSet(
                                String id,
                                @Nullable ImageInfo imageInfo,
                                @Nullable Animatable anim) {
                            Log.e(TAG, "onFinalImageSet");
                            if (anim != null) {
                                // app-specific logic to enable animation starting
                                anim.start();
                            }
                        }

                    };

                    AbstractDraweeController localAbstractDraweeController = Fresco
                            .newDraweeControllerBuilder()
                            .setOldController(bgImage.getController())
                            .setControllerListener(controllerListener)
                            .setImageRequest(localImageRequest)
                            .build();
                    bgImage.setController(localAbstractDraweeController);
                    return true;
                } else {
                    Uri uri = Uri.fromFile(bgFile);
                    bgImage.setImageURI(uri);
                }
            }
        }
        return false;
    }
}
