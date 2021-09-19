package com.example.studyhelper_android_firebase;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.cirLoginButton);

        login.setOnClickListener((View v) -> {
                            EditText Email= findViewById(R.id.editTextEmail);
                EditText Password= findViewById(R.id.editTextPassword);

                if((Email.getText().toString().equals("c"))&&(Password.getText().toString().equals("c"))){
                    Intent start = new Intent(this,Course_manager_home.class);
                    startActivity(start);
                }    else            if((Email.getText().toString().equals("t"))&&(Password.getText().toString().equals("t"))){
                Intent start = new Intent(this,TeacherMainActivity.class);
                startActivity(start);
            }else                 if((Email.getText().toString().equals("ch"))&&(Password.getText().toString().equals("ch"))){
                    Intent start = new Intent(this,ComplainMain.class);
                    startActivity(start);
                }else                if((Email.getText().toString().equals("s"))&&(Password.getText().toString().equals("s"))){
                Intent start = new Intent(this,StudentMainActivity.class);
                startActivity(start);
            }
        });
    }
}