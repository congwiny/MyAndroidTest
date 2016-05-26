package com.congwiny.spanstringdemo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
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

        SpannableString ss = new SpannableString("hello world haha @");

        //stringBuilder.append("@ hello world haha @");
        ss.setSpan(new RoundedBackgroundSpan(this),
                0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.e(TAG,"onClick");
                Toast.makeText(MainActivity.this,"tiptip",Toast.LENGTH_SHORT).show();
            }
        }, 12, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 6,
                10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Drawable platformDrawable = getResources().getDrawable(R.drawable.live_msg_share_facebook);
        int textSize = getResources().getDimensionPixelSize(R.dimen.room_public_chat_iconsize);
        platformDrawable.setBounds(0, 0, textSize, textSize);
        ImageSpan imageLevelSpan = new ImageSpan(platformDrawable, ImageSpan.ALIGN_BASELINE);
        ss.setSpan(imageLevelSpan, 17, 18, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        mHelloTv.setMovementMethod(LinkMovementMethod.getInstance());

        mHelloTv.setText(ss);
    }
}
