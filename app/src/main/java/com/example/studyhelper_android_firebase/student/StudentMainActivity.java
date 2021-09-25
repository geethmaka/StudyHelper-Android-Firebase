package com.example.studyhelper_android_firebase.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;


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

}