package com.congwiny.motionevent;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int ACTION_MASK = 0xff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_motion_cancel);

        //亲测，手触摸到button7,首先会执行action_down，然后稍微触动会执行action_move。。
        // 移动一定距离会执行action_cancel。。抬手action_up事件就不会被执行了。。
        //如果action_cancel没有执行，后续的触摸事件都能执行到。。点击，稍微触动,抬手 action7，执行action_down,action_move,action_up
        //action_cancel之后，button7再也收不到任何事件。。
        /**
         * "ACTION_CANCEL occurs when the parent takes possession of the motion
         * 意思是action_cancel发生的时机是当父控件消费了某次MotionEvent事件。。当前view后续的触摸事件就被取消了
         *
         * "ACTION_CANCEL occurs when the parent takes possession of the motion,
         * for example when the user has dragged enough across a list view
         * that it will start scrolling instead of letting you press the buttons inside of it.
         */
        findViewById(R.id.button7).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "action ACTION_DOWN" + event.getEdgeFlags());

                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "action ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "action ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "action ACTION_UP");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "action ACTION_OUTSIDE");
                        break;
                }

                return false;
            }
        });

        findViewById(R.id.tv_haha).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "tv action ACTION_DOWN action=" + action);

                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "tv action ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "tv action ACTION_MOVE action="+action);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "tv action ACTION_UP");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.e(TAG, "tv action ACTION_OUTSIDE");
                        break;
                }

                return false;
            }
        });

        //& 先转换为二进制，再计算
        /**
         * ACTION_MASK
         * 1111 1111
         * 0000 0010
         * ---------
         * 0000 0010 = 2
         */
        Log.e(TAG, "& result=" + (ACTION_MASK & 2));//2
    }
}
