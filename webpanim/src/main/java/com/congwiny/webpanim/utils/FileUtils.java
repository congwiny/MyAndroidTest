package com.congwiny.webpanim.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by congwiny on 2016/4/25.
 */
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public static String readFileText(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;

        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        Log.e(TAG,"load file text="+sb.toString());
        return sb.toString();
    }

    public static boolean isWebpFile(String fileName){
        return fileName.endsWith(".webp");
    }
}
