package com.congwiny.listviewdemo.task;

import android.os.AsyncTask;
import android.util.SparseArray;
import android.view.View;

import com.congwiny.listviewdemo.R;
import com.congwiny.listviewdemo.adapter.ListViewAdapter;
import com.congwiny.listviewdemo.bean.Download;

/**
 * Created by congwiny on 2016/8/29.
 */
public class MyAsyncTask extends AsyncTask<Download, Integer, Void> {

    private Download myDownload; //单个数据，用于完成后的处理
    private SparseArray<View> viewList; //视图对象集合，用于设置样式
    private Integer viewId; //视图标识，用于匹配视图对象

    public MyAsyncTask(SparseArray<View> viewList, Integer viewId) {
        this.viewList = viewList;
        this.viewId = viewId;
    }

    @Override
    protected Void doInBackground(Download... params) {
        myDownload = params[0]; /* 模拟下载任务 */
        for (int i = 0; i <= 100; i++) { //发布进度
            publishProgress(myDownload.progress++);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        View view = null; /* 匹配视图对象 */

        //由于列表滑动，每次更新都得去判断，去获取position对应的view对象
        for (int i = 0; i < viewList.size(); i++) {
            if (viewList.get(i).getTag(R.id.list_view) == viewId) {
                view = viewList.get(i);
                break;
            }
        }

        if (view != null) { //将视图对象中缓存的ViewHolder对象取出，并使用该对象对控件进行更新
            ListViewAdapter.ViewHolder viewHolder = (ListViewAdapter.ViewHolder) view.getTag();
            viewHolder.progressBar.setProgress(values[0]);

            if (values[0] == 100) {
                viewHolder.button.setText("FINISH");
            }
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) { //更新数据源信息
        myDownload.isComplete = true;
        myDownload.text = "FINISH";

    }
}