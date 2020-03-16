package com.viper.android.render.sample.cs;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;

import androidx.annotation.Nullable;

import com.viper.android.render.log.RLog;

public class SingleRenderService extends IntentService {

    private static final String TAG = "SingleRenderService";
    private final Handler mHandler = new Handler();
    private Handler mMainHandler = null;

    private MyPresentation mPresentation;
    private final IRenderInterface.Stub mRenderInterface = new IRenderInterface.Stub() {
        @Override
        public void prepare(Surface surface, int width, int height) throws RemoteException {
            RLog.info(TAG, "prepare");
            postPrepare(surface, width, height);
        }

        @Override
        public void dispatchTouchEvent(MotionEvent event) throws RemoteException {
            RLog.info(TAG, "dispatchTouchEvent");
            postDispatchTouchEvent(event);
        }

        @Override
        public void println(int value) throws RemoteException {
            RLog.info(TAG, "println %s", value);
        }
    };

    public SingleRenderService() {
        super("back-vd-render");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RLog.info(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RLog.info(TAG, "onDestroy");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mRenderInterface;
    }

    private void postPrepare(final Surface surface, final int width, final int height) {
        getPostHandler().post(new Runnable() {
            @Override
            public void run() {
                servicePrepare(surface, width, height);
            }
        });
    }

    private void postDispatchTouchEvent(final MotionEvent event) {
        runMain(new Runnable() {
            @Override
            public void run() {
                serviceDispatchTouchEvent(event);
            }
        });
    }

    private void servicePrepare(Surface surface, int width, int height) {
        int densityDpi = getContext().getResources().getDisplayMetrics().densityDpi;
        VirtualDisplay virtualDisplay = getDisplayManager().createVirtualDisplay(
                "flutter-vd",
                width,
                height,
                densityDpi,
                surface,
                0
        );
        MyPresentation presentation = new MyPresentation(this, virtualDisplay.getDisplay());
        mPresentation = presentation;
        presentation.show();
    }

    private Context getContext() {
        return this;
    }

    private DisplayManager getDisplayManager() {
        return (DisplayManager) getSystemService(DISPLAY_SERVICE);
    }

    private Handler getPostHandler() {
        return mHandler;
    }

    private void runMain(Runnable runnable) {
        if (mMainHandler == null) {
            mMainHandler = new Handler(Looper.getMainLooper());
        }
        mMainHandler.post(runnable);
    }

    private void serviceDispatchTouchEvent(MotionEvent event) {
        RLog.debug(TAG, "serviceDispatchTouchEvent ev=" + event.getAction());
        if (mPresentation != null && mPresentation.getView() != null) {
            mPresentation.getView().dispatchTouchEvent(event);
        }
    }
}
