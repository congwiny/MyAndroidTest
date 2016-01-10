package com.congwiny.mvptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.congwiny.mvptest.presenter.BasePresenter;

/**
 * Created by congwiny on 16/1/11.
 */
public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity{
    protected T mPresenter; //presenter对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
