package com.example.studyhelper_android_firebase.teacher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.Teacher_popup_Pdf;
import com.example.studyhelper_android_firebase.classes.ILink;
import com.example.studyhelper_android_firebase.classes.Link;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
//create teacher link class
public class Teacher_popup_Link extends Activity {

    public boolean checkForEmpty(String title, String subject,String time,String link) {
        if ((!title.equals("") && (!subject.equals("Subject"))&&(!time.equals(""))&&(!link.equals("")))) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_popup_link);

        //database connection
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button uploadButton = findViewById(R.id.btn_uploadlink);
        uploadButton.setOnClickListener((View v) -> {

            Spinner Subject =findViewById(R.id.spinner);
            EditText Title =findViewById(R.id.link_title);
            CalendarView Date =findViewById(R.id.calendarView);
            EditText Time =findViewById(R.id.Time);
            Spinner AmPm =findViewById(R.id.spinner2);
            EditText Link =findViewById(R.id.link_add);
            String time=Time.getText().toString()+AmPm.getSelectedItem().toString();
            String Name= Subject.getSelectedItem().toString();
            Long date=Date.getDate();
            if(Name == null) {
                Toast.makeText(getApplicationContext(),"Please select subject",Toast.LENGTH_LONG).show();}
            else if(TextUtils.isEmpty(Title.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter title",Toast.LENGTH_LONG).show();
            else if(date==null)
                Toast.makeText(getApplicationContext(),"Please select date",Toast.LENGTH_LONG).show();
            else if(TextUtils.isEmpty(Time.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter time",Toast.LENGTH_LONG).show();
            else if(TextUtils.isEmpty(Link.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter link",Toast.LENGTH_LONG).show();
            else {

            }
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String id =preferences.getString("uid","");

            ILink link=new ILink(id,Subject.getSelectedItem().toString(),Title.getText().toString(),Date.getDate(),time,Link.getText().toString());


            db.collection("link")
                    .add(link)
                    .addOnSuccessListener(documentReference -> Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e));
                     Toast.makeText(Teacher_popup_Link.this,"Successfully inserted",Toast.LENGTH_SHORT).show();
        });


    }

}
