package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000; // This is in milliseconds
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        mAuth =FirebaseAuth.getInstance();

        //Dong man minh sau 3 giay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Tao intent chuyen canh
                Intent intentMain = new Intent(WelcomeActivity.this, Loginactivity.class);
                WelcomeActivity.this.startActivity(intentMain);
                WelcomeActivity.this.finish();

//                FirebaseUser currenUser =mAuth.getCurrentUser();
//                if(currenUser != null){
//                    //user da dang nhap -> mainactivity
//                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                }else{
//                    //user chua dang nhap thi chuyen toi trang login
//                    startActivity(new Intent(WelcomeActivity.this, Loginactivity.class));
//
//                }
//                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}