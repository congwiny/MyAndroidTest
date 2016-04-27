package com.congwiny.webpanim.view;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.congwiny.webpanim.bean.GiftChatModel;
import com.congwiny.webpanim.bean.anim.AnimationEffect;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by congwiny on 2016/2/18.
 */
public class GiftAnimationView extends FrameLayout {
    private static final String TAG = GiftAnimationView.class.getSimpleName();
    public SimpleDraweeView mBackAnimView;
    public SimpleDraweeView mFrontAnimView;
    public GiftMoveAnimView mGiftMoveAnimView;
    private LinkedList<GiftChatModel> mGiftQueue = new LinkedList<>();

    public GiftAnimationView(Context context) {
        this(context, null);
    }

    public GiftAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GiftAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mBackAnimView = new SimpleDraweeView(context);
        addView(mBackAnimView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT));
        mGiftMoveAnimView = new GiftMoveAnimView(context);
        mGiftMoveAnimView.setGiftQueue(mGiftQueue);
        addView(mGiftMoveAnimView);
        mFrontAnimView = new SimpleDraweeView(context);
        addView(mFrontAnimView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT));

    }

    private void prepareBackground(String backWebpName) {
        if (!TextUtils.isEmpty("kiss3")) {
            File backWebp = new File("/sdcard/sololive/effect/kiss3", "kiss.webp");
            ImageRequest localImageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.fromFile(backWebp)).build();
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
                    .setOldController(mBackAnimView.getController())
                    .setImageRequest(localImageRequest)
                    .setControllerListener(controllerListener)
                    // .setAutoPlayAnimations(true)
                    .build();


            mBackAnimView.setController(localAbstractDraweeController);

            postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animatable animation = mBackAnimView.getController().getAnimatable();
                    if (animation != null) {
                        // 开始播放
                        animation.start();
                        // 一段时间之后，根据业务逻辑，停止播放
                       // animation.stop();
                    }
                }
            },12000);
        }
    }

    private void prepareFrontground(String frontWebpName) {
        if (!TextUtils.isEmpty(frontWebpName)) {
            File backWebp = new File("/sdcard/sololive/effect/ship3", frontWebpName);
            ImageRequest localImageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.fromFile(backWebp)).build();
            AbstractDraweeController localAbstractDraweeController = Fresco.newDraweeControllerBuilder().setOldController(mFrontAnimView.getController()).setImageRequest(localImageRequest).setAutoPlayAnimations(true).build();
            mFrontAnimView.setController(localAbstractDraweeController);
        }
    }

  /*  public void enqueueGift(GiftChatModel gift) {
        mGiftQueue.offer(gift);
        if (!mGiftViewOne.isWorking()) {
            mGiftViewOne.performAnim();
        } else if (!mGiftViewTwo.isWorking()) {
            mGiftViewTwo.performAnim();
        }
    }*/

    public void prepareAnim(AnimationEffect animationEffect) {
        setVisibility(VISIBLE);
        prepareBackground(animationEffect.getAnimationBackgroundName());
        // prepareFrontground(animationEffect.getAnimationForegroundName());
        //playWebp(paramJSONObject.optJSONArray("animationList"), 0);
    }

    private void performGiftAnimation(AnimationEffect animationEffect) {

    }
}
