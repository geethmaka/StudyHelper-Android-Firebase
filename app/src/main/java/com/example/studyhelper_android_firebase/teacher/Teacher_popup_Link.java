package com.example.studyhelper_android_firebase.teacher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Link;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Teacher_popup_Link extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_popup_link);

        Button upload=findViewById(R.id.btn_uploadlink);

        upload.setOnClickListener((View v)->{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
                String Subject =findViewById(R.id.spinner).toString();
                String Title =findViewById(R.id.link_title).toString();
                View Date = findViewById(R.id.calendarView);
                String Time =findViewById(R.id.Time).toString();
                String AmPm = findViewById(R.id.spinner2).toString();
                String Link = findViewById(R.id.link_add).toString();

                Link link=new Link(Subject,Title,Date.toString(),Time,AmPm,Link);

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
