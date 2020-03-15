package com.viper.android.render.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.viper.android.render.sample.cs.SingleBrowserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSingleProcessActivity(View view) {
        Intent intent = new Intent(this, SingleProcessActivity.class);
        startActivity(intent);
    }

    public void onSinglePresentationActivity(View view) {
        Intent intent = new Intent(this, SinglePresentationActivity.class);
        startActivity(intent);
    }

    public void onSingleBrowserActivity(View view) {
        Intent intent = new Intent(this, SingleBrowserActivity.class);
        startActivity(intent);
    }
}
