package com.example.studyhelper_android_firebase.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Link;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Teacher_popup_Link extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_popup_link);


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
            com.example.studyhelper_android_firebase.classes.Link link=new Link(Subject.getSelectedItem().toString(),Title.getText().toString(),Date.getDate(),time,Link.getText().toString());



            db.collection("link")
                    .add(link)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                        }
                    });
        });


    }

}
