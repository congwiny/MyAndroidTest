package com.congwiny.spanstringdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView mHelloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mHelloTv = (TextView) findViewById(R.id.tv_hello);

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        stringBuilder.append("hello world haha");

        stringBuilder.setSpan(new RoundedBackgroundSpan(this),
                0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.e(TAG,"onClick");
                Toast.makeText(MainActivity.this,"tiptip",Toast.LENGTH_SHORT).show();
            }
        }, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mHelloTv.setMovementMethod(LinkMovementMethod.getInstance());

        mHelloTv.setText(stringBuilder);
    }
}
