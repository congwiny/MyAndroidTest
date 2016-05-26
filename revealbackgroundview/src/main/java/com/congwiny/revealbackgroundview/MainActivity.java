package com.congwiny.revealbackgroundview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void next(View v) {
        int location[] = new int[2];
        v.getLocationOnScreen(location);//将当前控件的坐标值赋给数组
        location[0]+=v.getWidth()/2;//获取横坐标控件的中心位置
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
        //关闭Activity启动的动画，目的是为了显示自己的动画
        overridePendingTransition(0, 0);
    }

    public void next1(View v) {
        int location[] = new int[2];
        v.getLocationOnScreen(location);
        location[0]+=v.getWidth()/2;
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
