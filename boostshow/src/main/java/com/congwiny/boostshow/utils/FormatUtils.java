package com.congwiny.boostshow.utils;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对所需文字格式等 转化加工
 *
 * @author luxin
 */
public class FormatUtils {
    /**
     * 转换百分比
     *
     * @param used （分子）
     * @param all  （分母）
     * @return
     */
    public static String getPercent(long used, long all) {
        String percentStr = "";// 接受百分比的值
        try {
            double member = used * 1.0;
            double denominator = all * 1.0;
            double percent = member / denominator;
            Locale.setDefault(Locale.ENGLISH);
            DecimalFormat df1 = new DecimalFormat("#0%"); // ##.00%
            percentStr = df1.format(percent);
        } catch (Exception e) {
            percentStr = "";
        }
        return percentStr;
    }

    static int fun(double a) {
        if (a < (int) a + 0.5) {
            return (int) (a);
        } else {
            return (int) (a + 0.5);
        }
    }

    public static int getPercentNow(double freeM, double tatalM) {
        int percent = 0;
        try {
            double unused = freeM / tatalM;
            double used = 1 - unused;
            percent = fun(used * 100);
        } catch (Exception e) {

        }
        return Math.abs(percent);
    }
    /**
     * 转换成int类型进度
     *
     * @param progressStr
     * @return
     */
    public static int getIntPercent(String progressStr) {
        int percent = 0;
        try {
            String regEx = "[^0-9]";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(progressStr);
            String subStr = matcher.replaceAll("").trim();
            percent = Integer.parseInt(subStr);
        } catch (Exception e) {
            percent = 10;
        }
        return percent;
    }

    /**
     * 单位换算
     *
     * @param size
     * 单位为B
     * @param isInteger
     * 是否返回取整的单位
     * @return 转换后的单位
     */
    private static DecimalFormat fileIntegerFormat = new DecimalFormat("#0");
    private static DecimalFormat fileDecimalFormat = new DecimalFormat("#0.00");

    public static String formatMemorySize(long size, boolean isInteger) {
        String fileSizeString = "0MB";
        try {
            DecimalFormat df = isInteger ? fileIntegerFormat : fileDecimalFormat;
            if (size < 1024 && size > 0) {
                fileSizeString = df.format((double) size) + "B";
            } else if (size < 1024 * 1024) {
                fileSizeString = df.format((double) size / 1024) + "KB";
            } else if (size < 1024 * 1024 * 1024) {
                fileSizeString = df.format((double) size / (1024 * 1024)) + "MB";
            } else {
                fileSizeString = df.format((double) size / (1024 * 1024 * 1024)) + "GB";
            }
        } catch (Exception e) {
            fileSizeString = "0MB";
        }
        return fileSizeString;
    }

    /**
     * long值转换M
     * @param size
     * @return
     */
    public static String formatMemorySize(long size) {
        String fileSizeString = "0M";
        try {
            DecimalFormat df = fileIntegerFormat;

                fileSizeString = df.format((double) size / (800 * 800)) + "M";

        } catch (Exception e) {
            fileSizeString = "100M";
        }
        return fileSizeString;
    }
    /**
     * long值转换M
     * @param size
     * @return
     */
    public static int formatMemoryIntSize(long size) {
        String fileSizeString = "0M";
        try {
            DecimalFormat df = fileIntegerFormat;

            fileSizeString = df.format((double) size / (800 * 800)) + "M";

        } catch (Exception e) {
            fileSizeString = "100M";
        }
        return getIntPercent(fileSizeString);
    }
    /**
     * 颜色不同得文字
     *
     * @param usedM
     * @param totalM
     * @return
     */
    public static String formatHtmlMemorySize(long usedM, long totalM) {
        String str = "<font color='#333333'>" + formatMemorySize(usedM, false) + "</font>" + "<font color='#ff3939'>" + "/"
                + formatMemorySize(totalM, false) + "</font>";
        return str;
    }

    /**
     * 内存使用情况
     *
     * @param usedM
     * @param totalM
     * @return
     */
    public static String formatMemorySize(long usedM, long totalM) {
        String str = formatMemorySize(usedM, false) + "/" + formatMemorySize(totalM, false);
        return str;
    }

    /**
     * 转时间格式
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatTime(long time) {
        SimpleDateFormat dataTime = new SimpleDateFormat("yyyy-MM-dd");
        return dataTime.format(time);

    }

    /**
     * 替换掉各种影响排序得东西
     *
     * @param chines
     * @return
     */
    public static String replaceString(String chines) {
        return chines.trim().replace("《", "").replace("》", "").replace("！", "").replace("￥", "").replace("【", "")
                .replace("】", "").replace("（", "").replace("）", "").replace("－", "").replace("；", "").replace("：", "")
                .replace("”", "").replace("“", "").replace("。", "").replace("，", "").replace("、", "").replace("？", "")
                .replace(" ", "").replace("-", "").replace("\\s*", "").replaceAll(" +", "").replaceAll("\\s*", "");
    }
}
