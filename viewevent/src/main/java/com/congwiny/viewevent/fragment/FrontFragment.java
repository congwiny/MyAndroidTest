package com.congwiny.viewevent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.congwiny.viewevent.R;

/**
 * Created by congwiny on 15/9/28.
 */
public class FrontFragment extends BaseFragment {

    private static final String TAG = "FrontFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_front,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG,"onSingleTapUp");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG,"onFling x="+e1.getX()+",y="+e1.getY());
        return true;
    }
}
