package com.congwiny.materialdesign;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.congwiny.materialdesign.adapter.MyPagerAdapter;

/**
 * Created by congwiny on 2016/6/2.
 */
public class TabLayoutActivity extends AppCompatActivity {

    private static final String TAG = TabLayoutActivity.class.getSimpleName();
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        TabLayout.Tab tab1 = tabLayout.newTab().setIcon(R.drawable.emoji_activity_pressed);
        TabLayout.Tab tab2 = tabLayout.newTab().setIcon(R.drawable.emoji_animal_pressed);
        TabLayout.Tab tab3 = tabLayout.newTab().setIcon(R.drawable.emoji_flag_pressed);

        TabLayout.Tab tab4 = tabLayout.newTab().setIcon(R.drawable.emoji_food_pressed);
        TabLayout.Tab tab5 = tabLayout.newTab().setIcon(R.drawable.emoji_history_pressed);
        TabLayout.Tab tab6 = tabLayout.newTab().setIcon(R.drawable.emoji_objects_pressed);

        TabLayout.Tab tab7 = tabLayout.newTab().setIcon(R.drawable.emoji_smileys_pressed);
        TabLayout.Tab tab8 = tabLayout.newTab().setIcon(R.drawable.emoji_travel_pressed);
        TabLayout.Tab tab9 = tabLayout.newTab().setIcon(R.drawable.emoji_symbol_pressed);


        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.addTab(tab4);
        tabLayout.addTab(tab5);
        tabLayout.addTab(tab6);
        tabLayout.addTab(tab7);
        tabLayout.addTab(tab8);
        tabLayout.addTab(tab9);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).getIcon().clearColorFilter();
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
                tabLayout.getTabAt(position).getIcon().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
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
                tab.getIcon().clearColorFilter();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

        onPageChangeListener.onPageSelected(0);
    }
}
