package com.congwiny.materialdesign;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.congwiny.materialdesign.adapter.MyPagerAdapter;

/**
 * Created by congwiny on 2016/6/2.
 */
public class TabLayoutActivity extends AppCompatActivity {

    private static final String TAG = TabLayoutActivity.class.getSimpleName();
    ViewPager viewPager;
    TabLayout tabLayout;

    private String [] imageUrl = new String[]{
            "http://img3.imgtn.bdimg.com/it/u=3456201365,762679504&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=33319317,3472222185&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=418916911,863434674&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1278014138,1618577766&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2368235776,2982096908&fm=21&gp=0.jpg"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        TabLayout.Tab tab1 = tabLayout.newTab();
        TabLayout.Tab tab2 = tabLayout.newTab();
        TabLayout.Tab tab3 = tabLayout.newTab();

        TabLayout.Tab tab4 = tabLayout.newTab();
        TabLayout.Tab tab5 = tabLayout.newTab();
        TabLayout.Tab tab6 = tabLayout.newTab();

        TabLayout.Tab tab7 = tabLayout.newTab();
        TabLayout.Tab tab8 = tabLayout.newTab();
        TabLayout.Tab tab9 = tabLayout.newTab();


        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.addTab(tab4);
        tabLayout.addTab(tab5);


        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View view = View.inflate(this,R.layout.layout_tab,null);
            //http://c.csdnimg.cn/jifen/images/xunzhang/t/t2.png
            ImageView iv = (ImageView) view.findViewById(R.id.iv_tab);
            Glide.with(this).load(imageUrl[i]).into(iv);
            tabLayout.getTabAt(i).setCustomView(view);

        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        final MyPagerAdapter adapter = new MyPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        ViewPager.OnPageChangeListener onPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        };
        viewPager.addOnPageChangeListener(onPageChangeListener);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabSelected");
                viewPager.setCurrentItem(tab.getPosition(), false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

        onPageChangeListener.onPageSelected(0);
    }
}
