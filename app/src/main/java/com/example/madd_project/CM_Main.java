package com.example.madd_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CM_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_management);

        BottomNavigationView course_bottomNavigationView = findViewById(R.id.bottomNavCourse);
        NavController course_navController = Navigation.findNavController(this,R.id.courseManagerFragmentContainer);
        NavigationUI.setupWithNavController(course_bottomNavigationView, course_navController);


    }
}