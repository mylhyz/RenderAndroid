package com.viper.android.render.log;

import android.util.Log;

public class RLog {

    private static final String PREFIX = "[Render]";

    public static void debug(String tag, String format, Object... args) {
        println(Log.DEBUG, tag, format, args);
    }

    public static void info(String tag, String format, Object... args) {
        println(Log.INFO, tag, format, args);
    }

    public static void warn(String tag, String format, Object... args) {
        println(Log.WARN, tag, format, args);
    }

    public static void error(String tag, String format, Object... args) {
        println(Log.ERROR, tag, format, args);
    }

    private static void println(int priority, String tag, String format, Object... args) {
        Log.println(priority, tag, String.format(PREFIX + format, args));
    }
}
