package com.example.studyhelper_android_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studyhelper_android_firebase.classes.Link;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Teacher_popup_Pdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_popup_pdf);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button uploadButton = findViewById(R.id.btn_uplodpdf);
        uploadButton.setOnClickListener((View v) -> {

            Spinner Subject =findViewById(R.id.spinnerpdf);
            TextView Title =findViewById(R.id.editTextPdf);
            TextView Pdfupload =findViewById(R.id.editTextselect);


            com.example.studyhelper_android_firebase.classes.Pdf pdf=new Pdf(Subject.getSelectedItem().toString(),Title.getText().toString(),Pdfupload.getText().toString());



            db.collection("pdf")
                    .add(pdf)
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