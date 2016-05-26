package com.congwiny.emojitext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView mHelloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTv = (TextView) findViewById(R.id.tv_hello);

        int unicode = 0x1F300;
        Log.e(TAG,"convert char = "+getEmijoByUnicode(unicode));
        mHelloTv.setText(getEmijoByUnicode(unicode));
    }

    public String getEmijoByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
