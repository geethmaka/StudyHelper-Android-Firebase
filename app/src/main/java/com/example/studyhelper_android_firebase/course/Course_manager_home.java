package com.example.studyhelper_android_firebase.course;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

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
}