package com.example.studyhelper_android_firebase.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.ICourse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class UpdateCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button updateButton = findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        EditText subject = findViewById(R.id.updateSubject);
        Spinner stream = findViewById(R.id.updateStream);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        Switch availability = findViewById(R.id.updateAvailability);
        db.collection("courses")
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            ICourse course = document.toObject(ICourse.class);
                            subject.setText(course.getSubject());
                            availability.setChecked(course.isAvailability());
                        } else {
                            Log.d("TAG", "No such document");
                        }
                    } else {
                        Log.d("TAG", "get failed with ", task.getException());
                    }
                });

        updateButton.setOnClickListener((View v) -> {
            DocumentReference washingtonRef = db.collection("courses").document(id);

            washingtonRef
                    .update("subject", subject.getText().toString(), "stream", stream.getSelectedItem().toString(), "availability", availability.isChecked())
                    .addOnSuccessListener(aVoid -> Log.d("TAG", "DocumentSnapshot successfully updated!"))
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error updating document", e);
                        }
                    });
        });

        deleteButton.setOnClickListener((View v) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
            alertDialog.setTitle("hi");
            alertDialog.setMessage("this is my app");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog, ID) -> db.collection("courses").document(id)
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Intent i = new Intent(v.getContext(), ViewCourses.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(i);
                    })
                    .addOnFailureListener(e -> {
                    }));
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialog, id1) -> dialog.dismiss());
            alertDialog.show();
        });
    }
}