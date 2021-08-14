package com.example.madd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        NavigationUI.setupActionBarWithNavController(this, student_navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:

            case R.id.Logout:

        }
        return super.onOptionsItemSelected(item);
    }
}