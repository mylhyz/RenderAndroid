// IRenderInterface.aidl
package com.viper.android.render.sample.cs;

// Declare any non-default types here with import statements
import android.view.Surface;
import android.view.MotionEvent;

interface IRenderInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void prepare(in Surface surface,int width,int height);

    void dispatchTouchEvent(in MotionEvent event);

    void println(int value);
}
