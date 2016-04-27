package com.congwiny.webpanim.newview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.congwiny.webpanim.bean.GiftChatModel;

import java.util.LinkedList;

/**
 * Created by congwiny on 2016/4/26.
 */
public class GiftAnimView extends FrameLayout {

    private LinkedList<GiftChatModel> mGiftQueue = new LinkedList<>();
    private boolean isWorking = false;

    public GiftAnimView(Context context) {
        this(context, null);
    }

    public GiftAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GiftAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void enqueueGift(GiftChatModel giftChatModel){
        mGiftQueue.offer(giftChatModel);
        if (!isWorking){
            setVisibility(VISIBLE);
            prepareGiftAnim(giftChatModel);
        }

    }

    private void prepareGiftAnim(GiftChatModel giftChatModel) {


    }

    private void initView() {

    }
}
