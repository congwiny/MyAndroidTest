package com.congwiny.listviewdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;

import com.congwiny.listviewdemo.R;
import com.congwiny.listviewdemo.bean.Download;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by congwiny on 2016/8/26.
 */
public class ListViewAdapter extends BaseAdapter {
    private List<Download> mDownloads;
    private List<View> mViewList;

    public ListViewAdapter() {
        mDownloads = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDownloads.size();
    }

    @Override
    public Download getItem(int i) {
        return mDownloads.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setData(List<Download> downloads) {
        mDownloads = downloads;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        /* 初始化控件 */
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar);
            viewHolder.button = (Button) convertView.findViewById(R.id.btn);
            convertView.setTag(viewHolder);//记录ViewHolder对象，缓存控件实例
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /* 添加控件样式 */
        //略去……

        /* 设置按钮点击事件 */
        //略去……

        /* 标识View对象 */
        convertView.setTag(R.id.list_view, position);       //此处将位置信息作为标识传递
        mViewList.add(convertView);                          //将每个View添加到视图集合中

        /**
         * View.setTag(int Key,Object object)中的Key值必须唯一
         * 传入任何常量都是无效的，必须传入R.id中生成的值
         *
         * 标识并非用于识别View对象，而是识别View的状态
         * 就像警帽并非用于识别演员，而是识别演员当前扮演的角色
         *
         * View集合就像演员名单一样重要，如果没有它表演无从开展
         *
         * notifyDataSetChanged()虽然能更新列表，但是它是更新所有控件数据
         * 相比于选择某个控件进行更新，这种方法性能开销大，体验差
         */
        return convertView;
    }

    public static class ViewHolder {
        public Button button;
        public ProgressBar progressBar;
    }

}
