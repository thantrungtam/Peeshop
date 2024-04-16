package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Field;

public class Loginactivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText textEmailLogin, textPassLogin;
    private TextView textForget, textSignup;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_activity);

        mAuth = FirebaseAuth.getInstance();

        //Anh xa id

        textEmailLogin = findViewById(R.id.textEmailLogin);
        textPassLogin = findViewById(R.id.textPassLogin);
        textForget = findViewById(R.id.textForget);
        textSignup = findViewById(R.id.textSignup);
        btnLogin = findViewById(R.id.btnLogin);

        //XU li su kien khi click vao textview Quen mat khau
        textForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Loginactivity.this, ResetPassword.class));
            }
        });
        //XU li su kien khi click vao textview Dang ki ngay
        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Loginactivity.this, SignupActivity.class));
            }
        });
        //Xư li su kien khi click vao button danh nhap
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmailLogin.getText().toString();
                String pass = textPassLogin.getText().toString();
                signIn(email, pass);
            }
        });


    }

    //Tao ham signIn
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Danh nhap thanh cong
                            Toast.makeText(Loginactivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Loginactivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(Loginactivity.this, "Kiểm tra lại tài khoản hoặc mật khẩu!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }
