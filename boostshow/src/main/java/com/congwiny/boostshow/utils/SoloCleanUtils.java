package com.congwiny.boostshow.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoloCleanUtils {

    private static boolean matchText(byte[] buffer, int index, String text) {
        int N = text.length();
        if ((index + N) >= buffer.length) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            if (buffer[index + i] != text.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static long extractMemValue(byte[] buffer, int index) {
        while (index < buffer.length && buffer[index] != '\n') {
            if (buffer[index] >= '0' && buffer[index] <= '9') {
                int start = index;
                index++;
                while (index < buffer.length && buffer[index] >= '0' && buffer[index] <= '9') {
                    index++;
                }
                String str = new String(buffer, 0, start, index - start);
                return ((long) Integer.parseInt(str)) * 1024;
            }
            index++;
        }
        return 0;
    }

    @SuppressWarnings("unused")
    public static long getTotalRunMemory() {
        byte[] mBuffer = new byte[1024];
        try {
            long memTotal = 0;
            FileInputStream is = new FileInputStream("/proc/meminfo");
            int len = is.read(mBuffer);
            is.close();
            final int BUFLEN = mBuffer.length;
            for (int i = 0; i < len && memTotal == 0; i++) {
                if (matchText(mBuffer, i, "MemTotal")) {
                    i += 8;
                    memTotal = extractMemValue(mBuffer, i);
                    break;
                }
                while (i < BUFLEN && mBuffer[i] != '\n') {
                    i++;
                }
            }
            return Math.abs(memTotal);
        } catch (java.io.FileNotFoundException e) {
        } catch (java.io.IOException e) {
        }
        return 0;
    }

    public static long getFreeRunMemory(Context mContext) {
        MemoryInfo mi = null;
        try {
            ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
            mi = new MemoryInfo();
            am.getMemoryInfo(mi);
        } catch (OutOfMemoryError e) {

        } catch (Exception e) {

        }
        return Math.abs(mi.availMem);
    }

    /**
     * 获取已使用的运行内存
     *
     * @param context
     * @return
     */
    public static long getUsedRunMemory(Context context) {
        return getTotalRunMemory() - getFreeRunMemory(context);
    }

    /**
     * 当前内存使用百分比
     *
     * @param context
     * @return
     */
    public static int getRunMemPercent(Context context) {
        int percent = 0;
        String strPercent = FormatUtils.getPercent(getUsedRunMemory(context), getTotalRunMemory());
        if (!strPercent.equals("")) {
            percent = FormatUtils.getIntPercent(strPercent);
        } else {
            percent = 30;
        }
        return percent;
    }

    /**
     * 根据清理的百分比算出释放多少内存
     *
     * @return
     */
    public static String getFreeMem(int percent) {
        long allMem = getTotalRunMemory();
        float getPercent = percent / 100f;
        long result = (long) (allMem * getPercent);
        return FormatUtils.formatMemorySize(result);
    }

    /**
     * 获取清理结果~
     * @param context
     * @return
     */
    public static String getFreeMem(Context context){
        return getFreeMem(getFreePercent(context));
    }
    /**
     * 根据清理的百分比算出释放多少内存
     *
     * @return
     */
    public static int getFreeIntMem(int percent) {
        long allMem = getTotalRunMemory();
        float getPercent = percent / 100f;
        long result = (long) (allMem * getPercent);
        return FormatUtils.formatMemoryIntSize(result);
    }

    /**
     * 根据清理的百分比算出释放多少内存
     *
     * @return
     */
    public static long getFreeLongMem(int percent) {
        long allMem = getTotalRunMemory();
        float getPercent = percent / 100f;
        long result = (long) (allMem * getPercent);
        return result;
    }

    /**
     * 是否展示广告
     *
     * @param context
     * @return

    public static boolean isShowAdverDialog(Context context) {
        if (!Utils.isNetConnected(context)) {
            return false;
        }
        float getShowAdverPercent = SoloSettingsHelper.getPreferenceFloat(context,
                UpdateEntry.KEY_BOOST_SHOW_ADS_RATIO, 1.0f);
        double randomPercent = MathUtils.randomDouble(0.0, 1.0);
        if (randomPercent <= getShowAdverPercent) {
            return true;
        }
        return false;
    }
     */

/*    *//**
     * 是否是可清理的时间
     *
     * @param context
     * @return
     *
     *//*
    public static boolean isCleanTime(Context context) {
        PreferenceManagers prefManager = new PreferenceManagers(context);
        long lastCleanTime = prefManager.getLongPref(PreferenceManagers.KEY_LAST_CLEAN_TIME,0);
        boolean isClean;
        if (System.currentTimeMillis() - lastCleanTime > 60000) {
            isClean = true;
        } else {
            isClean = false;
        }
        return isClean;
    }*/


    /**
     * 获取释放了多少百分比的内存
     *
     * @param context
     * @return
     */
    public static int getFreePercent(Context context) {
        int before_boost = SoloCleanUtils.getRunMemPercent(context);
        //调用清理
        cleanApps(context, 200);
        int after_boost = SoloCleanUtils.getRunMemPercent(context);
        int freePercent = 0;
        if (before_boost > 75) {
            int randomNum = new Random().nextInt(15) + 1;
            freePercent = before_boost - after_boost + 10 + randomNum;
        } else {
            int randomNum = 2 + new Random().nextInt(5) + 1;
            freePercent = before_boost - after_boost;
            if (freePercent < 5) {
                freePercent = randomNum;
            } else {
                freePercent = freePercent + randomNum;
            }
        }
        return freePercent;
    }

    /**
     * 清理应用
     * @param context
     * @param killImportance
     */
    public static void cleanApps(final Context context, final int killImportance) {
        final int sdkVersion = android.os.Build.VERSION.SDK_INT;
        ArrayList<String> whiteList = new ArrayList<String>();
        whiteList.add("home.solo.launcher.free");
        whiteList.add("home.solo.plugin.locker");
        whiteList.add("home.solo.launcher.free.plugin");
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes = am.getRunningAppProcesses();
        int psize = processes.size();
        for (int i = 0; i < psize; i++) {
            String pkgName = processes.get(i).processName;
            try {
                PackageManager pm = context.getPackageManager();
                ApplicationInfo info = pm.getApplicationInfo(pkgName, PackageManager.GET_UNINSTALLED_PACKAGES
                        | PackageManager.GET_DISABLED_COMPONENTS);

                if ((processes.get(i).importance >= killImportance && ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) && !whiteList
                        .contains(processes.get(i).processName))) {
                    if (sdkVersion >= 8) {
                        am.killBackgroundProcesses(processes.get(i).processName);
                    } else {
                        am.restartPackage(processes.get(i).processName);
                    }
                }
            } catch (Exception e) {

            }
        }

    }
}
