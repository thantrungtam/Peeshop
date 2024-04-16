package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText textFirstName, textLastName, textEmail, textPass, textConfirmPass;
    private TextView textLogin;
    ImageView imgBack;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        //anh xạ id
        textFirstName = findViewById(R.id.textFirstName);
        textLastName = findViewById(R.id.textLastName);
        textEmail = findViewById(R.id.textEmail);
        textPass = findViewById(R.id.textPass);
        textConfirmPass = findViewById(R.id.textConfirmPass);
        textLogin = findViewById(R.id.textLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        imgBack = findViewById(R.id.imgBack);
        mAuth = FirebaseAuth.getInstance();

        //Nếu không điền đủ thông tin thì hiện lỗi

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = textFirstName.getText().toString();
                String lastName = textLastName.getText().toString();
                String email = textEmail.getText().toString();
                String password = textPass.getText().toString();
                String confirmPassword = textConfirmPass.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    //Nếu qua được bước đăng nhập thì tới bước xác thực tài khoản
                    //Nếu qua được bước đăng nhập thì tới bước xác thực tài khoản
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    Toast.makeText(SignupActivity.this, "Mã xác thực đã gửi về email của bạn!", Toast.LENGTH_SHORT).show();
                                                    //Chuyển hướng người dùng đến màn hình đăng nhập
                                                    Intent intent = new Intent(SignupActivity.this, Loginactivity.class);
                                                    startActivity(intent);
//                                                    finish(); //không thể quay lại trang signup bang nut back
                                                } else {
                                                    Toast.makeText(SignupActivity.this, "Lỗi xác thực tài khoản!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Lỗi tạo tài khoản!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        //Xử lí sự kiện đăng nhập với tài khoản google



        //Xử lí sự kiện chuyển activity khi click textview đăng nhâp;

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(SignupActivity.this, Loginactivity.class);
                startActivity(intentLogin);
                finish();
            }
        });

        //Xử lí sự kiện button back
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //quay lại sự kiện trước đó
            }
        });

    }
}