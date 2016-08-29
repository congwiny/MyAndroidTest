package com.congwiny.listviewdemo.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;

import com.congwiny.listviewdemo.R;
import com.congwiny.listviewdemo.bean.Download;
import com.congwiny.listviewdemo.task.MyAsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by congwiny on 2016/8/26.
 */
public class ListViewAdapter extends BaseAdapter {
    private static final String TAG = ListViewAdapter.class.getSimpleName();
    private List<Download> mDownloads;
    private SparseArray<View> mViewList;

    public ListViewAdapter() {
        mDownloads = new ArrayList<>();
        mViewList = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return mDownloads.size();
    }

    @Override
    public Download getItem(int position) {
        return mDownloads.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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

        final Download download = mDownloads.get(position);
        //根据取出的数据，对列表进行设置！
        viewHolder.progressBar.setProgress(download.progress);
        viewHolder.button.setText(download.text);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.progressBar.getProgress() == 0) {
                    //如果未开始下载，启动异步下载任务
                    MyAsyncTask asyncTask = new MyAsyncTask(mViewList, position);
                    //添加THREAD_POOL_EXECUTOR可启动多个异步任务
                    asyncTask.executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR, download);
                }
            }
        });
        /* 标识View对象 */
        //此处是重点，让view对象和位置绑定起来，拿到位置，就确定了view对象是哪个了！！
        convertView.setTag(R.id.list_view, position);       //此处将位置信息作为标识传递
        mViewList.put(position, convertView);                //将每个View添加到视图集合中
        /**
         * View.setTag(int Key,Object object)中的Key值必须唯一
         * 传入任何常量都是无效的，必须传入R.id中生成的值
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
