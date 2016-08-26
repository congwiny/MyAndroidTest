package com.congwiny.emojitext;

import android.graphics.Color;
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
        int unicode = 0x1F604;
        Log.e(TAG, "convert char = " + getEmijoByUnicode(unicode) + ",code point=" + Integer.toHexString(Character.codePointAt(getEmijoByUnicode(unicode), 0)));
        mHelloTv.setTextColor(Color.BLACK);
        mHelloTv.setText(getEmijoByUnicode(unicode));

        char emoji =  0x2328;
        Log.e(TAG,"is Defined"+Character.isDefined(emoji)+
                ",is ident"+Character.isIdentifierIgnorable(emoji)+
                ",is unicode"+Character.isUnicodeIdentifierPart(emoji) +
                ",is hightsurrogate"+Character.isHighSurrogate(emoji) +
                ",is ios"+Character.isISOControl(emoji)+
                ",is java iden"+Character.isJavaIdentifierPart(emoji)+
                ",is space"+Character.isSpaceChar(emoji)
        );
        mHelloTv.setText(Character.toString(emoji));
    }

    public String getEmijoByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
