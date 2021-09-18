package com.example.studyhelper_android_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void gotoCHProfile(View view) {
        final Context context = this;
        Intent intent = new Intent(context, ComplainMain.class);
        startActivity(intent);
    }
}