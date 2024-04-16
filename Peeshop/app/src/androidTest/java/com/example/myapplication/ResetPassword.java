    package com.example.myapplication;

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

    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;

    public class ResetPassword extends AppCompatActivity {
        private EditText textEmailReset;
        private ImageView imgResetBack;
        private Button btnReset;
        private FirebaseAuth  mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_reset_password2);
            mAuth = FirebaseAuth.getInstance();
            //Anh xa id
            textEmailReset = findViewById(R.id.textEmailReset);
            imgResetBack = findViewById(R.id.imgResetBack);
            btnReset = findViewById(R.id.btnReset);

            //Xu li su kien khi click back
            imgResetBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            //Xu li su kien khi click vao buttton xac nhanh
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = textEmailReset.getText().toString().trim(); //Loai bo khoang trang
                    if(textEmailReset.getText().length() == 0){
                        Toast.makeText(getApplication(), "Vui lòng nhập địa chỉ email!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resetPassword(email); //lay du lieu tu truong text email khi nguoi dung nhap truyen vao ham resetPassword

                }
            });
        }
        //Ham reset xu li gui link doi mat khau ve email nguoi dung
        private void resetPassword(String email){
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        //Neu gui email thanh cong thuc hien thong bao cho nguoi dung
                        Toast.makeText(ResetPassword.this,"Vui lòng truy cập email để đổi mật khẩu!", Toast.LENGTH_SHORT).show();
                    }else{
                        //Neu that bai
                        Toast.makeText(ResetPassword.this, "Lỗi! Kiểm tra lại email của bạn.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }