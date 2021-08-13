package com.example.madd_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CH_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_main);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView bottomNavigationView = findViewById(R.id.chbottomNav);
        NavController navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        NavigationUI.setupActionBarWithNavController(this, navController);


//        String appBarConfig = AppBarConfiguration(setOf(R.id.ch_DashboardFragment,R.id.ch_ComplainFragment,R.id.ch_UserDetailsFragment,R.id.ch_ReportFragment));
    }
}