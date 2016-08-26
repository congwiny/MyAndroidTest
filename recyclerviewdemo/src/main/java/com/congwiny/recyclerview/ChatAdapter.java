package com.congwiny.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by congwiny on 2016/6/7.
 */
public class ChatAdapter extends RecyclerView.Adapter {

    private List<Message> messageList = new ArrayList<>();
    private LayoutInflater mInflater;

    public ChatAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatViewHolder(mInflater.inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatViewHolder viewHolder = (ChatViewHolder) holder;
        viewHolder.mChatTv.setText(messageList.get(position).text);
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView mChatTv;

        public ChatViewHolder(View itemView) {
            super(itemView);
            mChatTv = (TextView) itemView.findViewById(R.id.tv_chat);
        }
    }

    public void addMessage(Message message){
        messageList.add(message);
        notifyDataSetChanged();
    }
}
