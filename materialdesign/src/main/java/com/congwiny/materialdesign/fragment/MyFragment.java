package com.congwiny.materialdesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.congwiny.materialdesign.MainActivity;
import com.congwiny.materialdesign.R;

public class MyFragment extends Fragment {

    private Button mClickBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tabContent = (TextView) view.findViewById(R.id.textView);
        tabContent.setText("I am in tab: " + getArguments().getString("tab"));

        mClickBtn = (Button) view.findViewById(R.id.click_btn);
        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getContext(), MainActivity.class);
                intent.putExtra("datadata", 200);
                startActivity(intent);
            }
        });
    }
}