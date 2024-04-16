package com.example.myapplication.activity;

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

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Loginactivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText textEmailLogin, textPassLogin;
    private Button btnLogin;
    private TextView textSignup, textForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_activity);

        mAuth = FirebaseAuth.getInstance();
        textEmailLogin = findViewById(R.id.textEmailLogin);
        textPassLogin = findViewById(R.id.textPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        textSignup = findViewById(R.id.textSignup);
        textForget = findViewById(R.id.textForget);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn(v);

            }

        });

        textForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginactivity.this, ResetPassword.class));
            }
        });

        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginactivity.this, SignupActivity.class));
            }
        });
    }

    public void singIn(View view){
        String email = textEmailLogin.getText().toString();
        String pass = textPassLogin.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(Loginactivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){

                            Toast.makeText(Loginactivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT ).show();
                            startActivity(new Intent(Loginactivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(Loginactivity.this, "Tài khoản hoặc mật khẩu không hợp lệ!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}