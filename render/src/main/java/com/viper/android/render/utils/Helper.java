package com.viper.android.render.utils;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class Helper {
    public static String action(int action) {
        switch (action) {
            case ACTION_MOVE:
                return "move";
            case ACTION_UP:
                return "up";
            case ACTION_DOWN:
                return "down";
            default:
                return "" + action;
        }
    }
}
