package com.congwiny.webpanim.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.congwiny.webpanim.bean.GiftChatModel;

import java.util.LinkedList;

/**
 * Created by congwiny on 2016/4/24.
 */
public class GiftMoveAnimView extends RelativeLayout {
    private LinkedList<GiftChatModel> mGiftQueue = new LinkedList<>();

    public GiftMoveAnimView(Context context) {
        super(context);
    }

    public GiftMoveAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GiftMoveAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGiftQueue(LinkedList<GiftChatModel> giftQueue) {
        mGiftQueue = giftQueue;
    }

    public void setWebpPath() {

    }
}
