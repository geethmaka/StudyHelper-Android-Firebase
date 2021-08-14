package com.example.madd_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

//Control Bottom navigation bar
    BottomNavigationView teacher_bottomNavigationView = findViewById(R.id.tmbottomNav);
    NavController teacher_navController = Navigation.findNavController(this, R.id.teacherfragmentContainer);
    NavigationUI.setupWithNavController(teacher_bottomNavigationView,teacher_navController);
    }
}