package com.congwiny.listviewdemo.bean;

/**
 * Created by congwiny on 2016/8/26.
 */
public class Download {
    public int id;
    public boolean isComplete;//是否下载完成
    public String text = "DOWNLOAD";//按钮文字
    public int progress;


    public Download(int id) {
        this.id = id;
    }
}
