package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class WelcomeActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000; // This is in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        //Dong man minh sau 3 giay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Tao intent chuyen canh
                Intent intentMain = new Intent(WelcomeActivity.this, MainActivity.class);
                WelcomeActivity.this.startActivity(intentMain);
                WelcomeActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}