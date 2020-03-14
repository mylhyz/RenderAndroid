package com.viper.android.render.sample.presentation;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.viper.android.render.sample.R;

public class MyPresentation extends Presentation {

    public MyPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    public MyPresentation(Context outerContext, Display display, int theme) {
        super(outerContext, display, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_my);
        Button button = findViewById(R.id.btn_go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "show", Toast.LENGTH_LONG).show();
            }
        });
    }

    public View getView() {
        return findViewById(R.id.root_view);
    }
}
