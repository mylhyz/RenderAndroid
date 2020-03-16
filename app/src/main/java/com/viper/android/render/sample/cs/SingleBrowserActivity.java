package com.viper.android.render.sample.cs;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.viper.android.render.log.RLog;
import com.viper.android.render.sample.R;

public class SingleBrowserActivity extends AppCompatActivity {

    private static final String TAG = "SingleBrowserActivity";

    private ServiceConnection mServiceConnection;
    private IRenderInterface mRenderInterface;

    private Surface mSurface = null;
    private int mWidth = 0;
    private int mHeight = 0;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_browser);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                RLog.info(TAG, "onServiceConnected");
                IRenderInterface iRenderInterface = IRenderInterface.Stub.asInterface(service);
                try {
                    mRenderInterface = iRenderInterface;
                    tryPrepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                RLog.info(TAG, "onServiceDisconnected");
            }
        };
        Intent intent = new Intent(this, SingleRenderService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

        TextureView textureView = findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mSurface = new Surface(surface);
                mWidth = width;
                mHeight = height;
                tryPrepare();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        textureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                RLog.debug(TAG, "onTouch ev=" + event.getAction());
                tryDispatchTouchEvent(event);
                return true;
            }
        });
    }

    private void tryPrepare() {
        try {
            if (mRenderInterface != null && mSurface != null) {
//                mRenderInterface.println(123);
                mRenderInterface.prepare(mSurface, mWidth, mHeight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryDispatchTouchEvent(MotionEvent event) {
        try {
            if (mRenderInterface != null) {
                mRenderInterface.dispatchTouchEvent(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, SingleRenderService.class);
        unbindService(mServiceConnection);
        stopService(intent);
        super.onDestroy();
    }
}
