package com.example.studyhelper_android_firebase.course;


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


public class Course_manager_home extends AppCompatActivity {
    public static final String TAG = "YOUR-TAG-NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_manager_home);

        //Control Bottom navigation bar
        BottomNavigationView course_bottomNavigationView = findViewById(R.id.course_bottom_nav);
        NavController course_navController = Navigation.findNavController(this, R.id.coursefragmentContainer);
        NavigationUI.setupWithNavController(course_bottomNavigationView, course_navController);
        NavigationUI.setupActionBarWithNavController(this, course_navController);

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