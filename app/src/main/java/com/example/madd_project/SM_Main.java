package com.example.madd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SM_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_main);
    }
    public void gotosmcompalin(View view) {
        final Context context = this;
        Intent intent = new Intent(context, sm_complain.class);
        startActivity(intent);
    }
    public void gotoedit(View view) {
        final Context context = this;
        Intent intent = new Intent(context, sm_edit.class);
        startActivity(intent);
    }
    public void gotorequestquestion(View view) {
        final Context context = this;
        Intent intent = new Intent(context, sm_rq.class);
        startActivity(intent);
    }

}