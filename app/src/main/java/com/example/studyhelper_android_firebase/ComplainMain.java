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

public class ComplainMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Control the bottom navigation bar
        BottomNavigationView complain_bottomNavigationView = findViewById(R.id.chbottomNav);
        NavController complain_navController = Navigation.findNavController(this,R.id.chfragmentContainerView);
        NavigationUI.setupWithNavController(complain_bottomNavigationView, complain_navController);
        NavigationUI.setupActionBarWithNavController(this, complain_navController);

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
}