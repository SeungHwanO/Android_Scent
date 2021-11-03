package com.example.scent_project;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterRequset extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText editTextEail;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button buttonJoin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEail = (EditText) findViewById(R.id.editText_email);
        editTextPassword = (EditText) findViewById(R.id.editText_passWord);
        editTextName = (EditText) findViewById(R.id.editText_name);

        buttonJoin = (Button) findViewById(R.id.btn_join);
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextEail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                    // if 이메일과 비밀번호가 공백이 아닌 경우
                    createUser(editTextEail.getText().toString(),editTextPassword.getText().toString().equals(""));
                } else {
                    // else
                    Toast.makeText(RegisterRequset.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createUser(String toString, boolean equals) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCanceledListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterRequset.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterRequset.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
