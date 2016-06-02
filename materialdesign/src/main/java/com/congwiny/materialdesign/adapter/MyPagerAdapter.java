package com.congwiny.materialdesign.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.congwiny.materialdesign.fragment.MyFragment;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab", String.valueOf(position + 1));
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}