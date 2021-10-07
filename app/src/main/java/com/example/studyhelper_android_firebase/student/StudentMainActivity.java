package com.example.studyhelper_android_firebase.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.studyhelper_android_firebase.Login;
import com.example.studyhelper_android_firebase.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmain);

        //Control the bottom navigation bar
        BottomNavigationView student_bottomNavigationView = findViewById(R.id.studentbottomNaviagtionview);
        NavController student_navController = Navigation.findNavController(this, R.id.sfragmentContainerView);
        NavigationUI.setupWithNavController(student_bottomNavigationView, student_navController);
        NavigationUI.setupActionBarWithNavController(this, student_navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ch_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout:

        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(MenuItem item) {
        SharedPreferences myPrefs = getSharedPreferences("uid", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(this, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(i);
        finish();
    }

}