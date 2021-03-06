package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.studyhelper_android_firebase.Login;
import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.Teacher_popup_Pdf;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class TeacherMainActivity extends AppCompatActivity {
    //    RecyclerView Teacher_recycleView;
//    Teacher_adapter teacher_adapter;
    private EditText title, time, link;
    private Spinner subject, ampm;
    private Button upload;
    private CalendarView date;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        //Control Bottom navigation bar
        BottomNavigationView teacher_bottomNavigationView = findViewById(R.id.tmbottomNav);
        NavController teacher_navController = Navigation.findNavController(this, R.id.teacherfragmentContainer);
        NavigationUI.setupWithNavController(teacher_bottomNavigationView, teacher_navController);
        NavigationUI.setupActionBarWithNavController(this, teacher_navController);

    }

    //intents for page navigation
    public void gotoTmLink(View view) {
        final Context context = this;
        Intent intent = new Intent(context, Teacher_popup_Link.class);
        startActivity(intent);
    }

    public void gotoTmPdf(View view) {
        final Context context = this;
        Intent intent = new Intent(context, Teacher_popup_Pdf.class);
        startActivity(intent);
    }

    public void gotoTmComplain(View view) {
        final Context context = this;
        Intent intent = new Intent(context, Added_complain_T.class);
        startActivity(intent);
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

    //logout from teacher
    public void logout(MenuItem item) {
        SharedPreferences myPrefs = getSharedPreferences("uid", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(this, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(i);
        finish();
    }
}

//
//
//
//
//        subject = findViewById(R.id.spinner);
//        title = findViewById(R.id.link_title);
//        date = findViewById(R.id.calendarView);
//        time = findViewById(R.id.Time);
//        link = findViewById(R.id.link_add);
//        ampm = findViewById(R.id.spinner2);
//        upload = findViewById(R.id.btn_uploadlink);
//
//
//        db = FirebaseFirestore.getInstance();
//
//        upload.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String Subject = subject.getSelectedItem().toString();
//                String Title = title.getText().toString();
//                long Date = date.getDate();
//                String Time = time.getText().toString();
//                String AmPm = ampm.getSelectedItem().toString();
//                String Link = link.getText().toString();
//                String ID = UUID.randomUUID().toString();
//
//                saveToFireStore(ID, Subject, Title, Date, Title, AmPm, Link);
//            }
//
//        });
//    }
//
//    private void saveToFireStore(String id, String subject, String title, long date, String title1, String amPm, String link) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("subject", subject);
//        map.put("title", title);
//        map.put("date", date);
//        map.put("time", time);
//        map.put("link", link);
//        map.put("id", id);
//
//        db.collection("links").document(id).set(map)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(TeacherMainActivity.this, "date saved", Toast.LENGTH_SHORT
//
//
//                            );
//                        }
//
//                    }
//                });


//        Teacher_recycleView=findViewById(R.id.recyclerView);
//        Teacher_recycleView.setLayoutManager(context:this);
//
//        teacher_adapter=new Teacher_adapter(c this, getTeacherList());


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
//    public void onButtonShowPdfPopupWindowClick(View view) {
//
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.pdf_links_teacher, null);
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow pdfPopupWindow = new PopupWindow(popupView, width, height, focusable);
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window token
//        pdfPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                pdfPopupWindow.dismiss();
//                return true;
//            }
//        });
//    }
//
//
//    //Link Popup view
//    public void onButtonShowLinkPopupWindowClick(View view) {
//
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.teacher_popup_link, null);
//        // create the popup window
//        int width = LinearLayout.LayoutParams.MATCH_PARENT;
//        int height = LinearLayout.LayoutParams.MATCH_PARENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow linkPopupWindow = new PopupWindow(popupView, width, height, focusable);
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window token
//        linkPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                linkPopupWindow.dismiss();
//                return true;
//            }
//        });
//
//    }








