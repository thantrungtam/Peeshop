package com.example.myapplication.activity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText textEmailReset;
    private Button btnReset;
    private ImageView imgResetBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password2);


        mAuth = FirebaseAuth.getInstance();
        btnReset = findViewById(R.id.btnReset);
        imgResetBack = findViewById(R.id.imgResetBack);
        textEmailReset = findViewById(R.id.textEmailReset);



        //Xu li su kien khi nhan vao nut reset passwotd

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmailReset.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(ResetPassword.this, "Vui lòng nhập địa chủ email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ResetPassword.this, "Email khôi phục mật khẩu đã được gửi!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ResetPassword.this, "Email không chính xác!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Xử lí sự kiện button back
        imgResetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //quay lại sự kiện trước đó
            }
        });



    }
}