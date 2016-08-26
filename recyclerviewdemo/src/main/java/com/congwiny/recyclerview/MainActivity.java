package com.congwiny.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mChatRecyclerView;
    private ChatAdapter mChatAdapter;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChatRecyclerView = (RecyclerView) findViewById(R.id.recycler_chat);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mChatAdapter = new ChatAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        mChatRecyclerView.setLayoutManager(layoutManager);
        mChatRecyclerView.setAdapter(mChatAdapter);
    }

    public void sendMessage(View view) {
        String message = mEditText.getText().toString();
        if (!TextUtils.isEmpty(message)) {
            Message msg = new Message();
            msg.text = message;
            mChatAdapter.addMessage(msg);
           // mChatRecyclerView.scrollToPosition(mChatAdapter.getItemCount() - 1);
        }
    }
}
