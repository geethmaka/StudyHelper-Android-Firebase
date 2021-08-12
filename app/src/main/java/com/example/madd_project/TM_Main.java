package com.example.madd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TM_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm_main);

    }
    //Navigation between buttons
    public void gotoPdf(View view){
        final Context context = this;
        Intent intent = new Intent(context,tm_pdf.class);
        startActivity(intent);

    }
    public void gotoLink(View view){
        final Context context = this;
        Intent intent = new Intent(context,tm_link.class);
        startActivity(intent);
    }
    public void gotoComplain(View view){
        final Context context = this;
        Intent intent = new Intent(context,tm_complain.class);
        startActivity(intent);

    }
}