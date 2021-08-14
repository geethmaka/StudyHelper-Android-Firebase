package com.example.madd_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SM_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_main2);

        //Control bottom navigation bar
        BottomNavigationView student_bottomNavigationView = findViewById(R.id.sm_bottomnavi);
        NavController student_navController = Navigation.findNavController(this,R.id.student_fragmentContainerView);
        NavigationUI.setupWithNavController(student_bottomNavigationView, student_navController);
//        NavigationUI.setupActionBarWithNavController(this, navController);

    }
}