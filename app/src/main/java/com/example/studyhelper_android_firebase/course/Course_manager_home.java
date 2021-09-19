package com.example.studyhelper_android_firebase.course;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Course_manager_home extends AppCompatActivity {
    public static final String TAG = "YOUR-TAG-NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_manager_home);

        //Control Bottom navigation bar
        BottomNavigationView course_bottomNavigationView = findViewById(R.id.course_bottom_nav);
        NavController course_navController = Navigation.findNavController(this, R.id.coursefragmentContainer);
        NavigationUI.setupWithNavController(course_bottomNavigationView, course_navController);
        NavigationUI.setupActionBarWithNavController(this, course_navController);


//        Button buttonChoose = findViewById(R.id.buttonChoose);
//        Button buttonUpload = findViewById(R.id.buttonUpload);
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        TextView text= findViewById(R.id.textView1);
//        db.collection("courses")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.d(TAG, document.getId() + " => " + document.getData());
//                            text.append(document.getData().toString());
//                        }
//                    } else {
//                        Log.w(TAG, "Error getting documents.", task.getException());
//                        text.setText(task.getException().toString());
//                    }
//                });

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        buttonChoose.setOnClickListener((View v) -> {
//
//            User user = new User("test", "email");
//
//            db.collection("users")
//                    .add(user)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error adding document", e);
//                        }
//                    });
//        });
//        buttonUpload.setOnClickListener((View v) -> {
//            DocumentReference washingtonRef = db.collection("users").document(
//                    "bvAuNVtGERJ12VOORNZR");
//
//            washingtonRef
//                    .update("email", "dd")
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d(TAG, "DocumentSnapshot successfully updated!");
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error updating document", e);
//                        }
//                    });
//        });
    }
}