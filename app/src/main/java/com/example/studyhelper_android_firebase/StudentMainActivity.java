package com.example.studyhelper_android_firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmain);

        //Control the bottom navigation bar
        BottomNavigationView complain_bottomNavigationView = findViewById(R.id.studentbottomNaviagtionview);
        NavController complain_navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(complain_bottomNavigationView, complain_navController);
        NavigationUI.setupActionBarWithNavController(this, complain_navController);

/*      BottomNavigationView student_bottomNavigationView = findViewById(R.id.studentbottomNaviagtionview);
        NavController student_navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(student_bottomNavigationView, student_navController);
        NavigationUI.setupActionBarWithNavController(this, student_navController);*/
    }

}