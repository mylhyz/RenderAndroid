package com.viper.android.render.sample;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.viper.android.render.sample.presentation.MyPresentation;

public class SinglePresentationActivity extends AppCompatActivity {

    private MyPresentation mPresentation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_presentation);

        TextureView textureView = findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                prepare(new Surface(surfaceTexture), width, height);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        });
        textureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mPresentation != null && mPresentation.getView() != null) {
                    mPresentation.getView().dispatchTouchEvent(motionEvent);
                }
                return true;
            }
        });
    }

    private void prepare(final Surface surface, int width, int height) {
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
}
