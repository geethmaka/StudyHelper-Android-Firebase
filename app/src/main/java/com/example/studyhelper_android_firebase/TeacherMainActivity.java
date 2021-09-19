package com.example.studyhelper_android_firebase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherMainActivity extends AppCompatActivity {
//    RecyclerView Teacher_recycleView;
//    Teacher_adapter teacher_adapter;
private EditText title,date,time,link;
private Spinner subject;
private Button upload;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teacher_main);

//        Teacher_recycleView=findViewById(R.id.recyclerView);
//        Teacher_recycleView.setLayoutManager(context:this);
//
//        teacher_adapter=new Teacher_adapter(c this, getTeacherList());



//Control Bottom navigation bar
    BottomNavigationView teacher_bottomNavigationView = findViewById(R.id.tmbottomNav);
    NavController teacher_navController = Navigation.findNavController(this, R.id.teacherfragmentContainer);
    NavigationUI.setupWithNavController(teacher_bottomNavigationView, teacher_navController);
    NavigationUI.setupActionBarWithNavController(this, teacher_navController);


}
//@Override
//public boolean onCreateOptionsMenu(Menu menu) {
//    MenuInflater inflater = getMenuInflater();
//    inflater.inflate(R.menu.options_menu, menu);
//    return true;
//}
//
//@Override
//public boolean onOptionsItemSelected(MenuItem item) {
//    switch (item.getItemId()) {
//        case R.id.settings:
//
//        case R.id.Logout:
//
//    }
//    return super.onOptionsItemSelected(item);

//Pdf Popout view
public void onButtonShowPdfPopupWindowClick(View view) {

    // inflate the layout of the popup window
    LayoutInflater inflater = (LayoutInflater)
            getSystemService(LAYOUT_INFLATER_SERVICE);
    View popupView = inflater.inflate(R.layout.pdf_links_teacher, null);

    // create the popup window
    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
    boolean focusable = true; // lets taps outside the popup also dismiss it
    final PopupWindow pdfPopupWindow = new PopupWindow(popupView, width, height, focusable);

    // show the popup window
    // which view you pass in doesn't matter, it is only used for the window token
    pdfPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    // dismiss the popup window when touched
    popupView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            pdfPopupWindow.dismiss();
            return true;
        }
    });
}

//Link Popup view
public void onButtonShowLinkPopupWindowClick(View view) {

    // inflate the layout of the popup window
    LayoutInflater inflater = (LayoutInflater)
            getSystemService(LAYOUT_INFLATER_SERVICE);
    View popupView = inflater.inflate(R.layout.link_popup_teacher, null);

    // create the popup window
    int width = LinearLayout.LayoutParams.MATCH_PARENT;
    int height = LinearLayout.LayoutParams.MATCH_PARENT;
    boolean focusable = true; // lets taps outside the popup also dismiss it
    final PopupWindow linkPopupWindow = new PopupWindow(popupView, width, height, focusable);

    // show the popup window
    // which view you pass in doesn't matter, it is only used for the window token
    linkPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    // dismiss the popup window when touched
    popupView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            linkPopupWindow.dismiss();
            return true;
        }
    });

}

}


