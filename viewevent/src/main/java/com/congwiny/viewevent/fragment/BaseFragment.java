package com.congwiny.viewevent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.congwiny.viewevent.R;

/**
 * Created by congwiny on 15/9/27.
 */
public class BaseFragment extends Fragment implements GestureDetector.OnGestureListener, View.OnTouchListener {

    private static final String TAG = "FrontFragment";
    private GestureDetector mDetector;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDetector = new GestureDetector(getActivity(),this);
        view.setOnTouchListener(this);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG,"onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG,"onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG,"onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG,"onFling");
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return mDetector.onTouchEvent(event);
    }
}
