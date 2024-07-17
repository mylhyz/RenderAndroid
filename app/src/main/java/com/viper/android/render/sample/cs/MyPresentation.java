package com.viper.android.render.sample.cs;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;

import com.viper.android.render.log.RLog;
import com.viper.android.render.sample.R;

public class MyPresentation extends Presentation {

    public MyPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_my);

        findViewById(R.id.btn_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIXME 无法直接弹窗
//                Toast.makeText(getContext(), "Hello", Toast.LENGTH_LONG).show();
            }
        });
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                RLog.debug("VIPER", "onLongPress");
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                RLog.debug("VIPER", "onDoubleTap");
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                RLog.debug("VIPER", "onSingleTapConfirmed");
                return super.onSingleTapConfirmed(e);
            }
        };
        final GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(getContext(), simpleOnGestureListener);
        findViewById(R.id.touch_view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetectorCompat.onTouchEvent(event);
            }
        });
    }


    public View getView() {
        return findViewById(R.id.root_view);
    }
}
