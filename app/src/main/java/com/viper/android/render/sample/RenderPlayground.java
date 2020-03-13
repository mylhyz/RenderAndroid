package com.viper.android.render.sample;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.viper.android.render.log.RLog;

import java.util.List;

public class RenderPlayground extends Application {

    private static final String TAG = "RenderPlayground";

    @Override
    public void onCreate() {
        super.onCreate();
        RLog.info(TAG, "current pId=%s,pName=%s", android.os.Process.myPid(), getCurrentProcessName(this));
    }

    private static String getCurrentProcessName(Context context) {
        if (context == null) {
            return "null1";
        }
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        if (activityManager == null) {
            return "null2";
        }
        List<ActivityManager.RunningAppProcessInfo> infos = activityManager.getRunningAppProcesses();
        if (infos == null || infos.isEmpty()) {
            return "null3";
        }
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid) {
                return info.processName;
            }
        }
        return "null";
    }
}
