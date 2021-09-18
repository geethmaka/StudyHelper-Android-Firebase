package com.example.studyhelper_android_firebase;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.cirLoginButton);

        login.setOnClickListener((View v) -> {
                            EditText Email= findViewById(R.id.editTextEmail);
                EditText Password= findViewById(R.id.editTextPassword);

                if((Email.getText().toString().equals("course"))&&(Password.getText().toString().equals("course"))){
                    Intent start = new Intent(this,Course_manager_home.class);
                    startActivity(start);
                }
        });
    }

    public void gotoCHProfile(View view) {
        final Context context = this;
        Intent intent = new Intent(context, ComplainMain.class);
        startActivity(intent);
    }
}