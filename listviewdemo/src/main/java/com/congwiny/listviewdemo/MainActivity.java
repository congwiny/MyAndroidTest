package com.congwiny.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.congwiny.listviewdemo.adapter.ListViewAdapter;
import com.congwiny.listviewdemo.bean.Download;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_view);
        ListViewAdapter adapter = new ListViewAdapter();
        mListView.setAdapter(adapter);

        List<Download> downloads = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            downloads.add(new Download(i));
        }
        adapter.setData(downloads);
    }
}
