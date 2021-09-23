package com.example.studyhelper_android_firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.User;
import com.example.studyhelper_android_firebase.complain.ComplainMain;
import com.example.studyhelper_android_firebase.course.Course_manager_home;
import com.example.studyhelper_android_firebase.course.RecyclerViewAdapter;
import com.example.studyhelper_android_firebase.student.StudentMainActivity;
import com.example.studyhelper_android_firebase.teacher.TeacherMainActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.cirLoginButton);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ArrayList<User> userList = new ArrayList<>();


        login.setOnClickListener((View v) -> {
            EditText Email = findViewById(R.id.editTextEmail);
            EditText Password = findViewById(R.id.editTextPassword);
            db.collection("users")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User u=new User(document.getId(),document.toObject(User.class));
                                userList.add(u);
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    });

            for(User u : userList){
                if ((Email.getText().toString().equals(u.getUser().getEmail())) && (Password.getText().toString().equals(u.getUser().getPassword()))) {
                    Intent start = new Intent(this, Course_manager_home.class);
                    startActivity(start);
                } else if ((Email.getText().toString().equals("t")) && (Password.getText().toString().equals("t"))) {
                    Intent start = new Intent(this, TeacherMainActivity.class);
                    startActivity(start);
                } else if ((Email.getText().toString().equals("ch")) && (Password.getText().toString().equals("ch"))) {
                    Intent start = new Intent(this, ComplainMain.class);
                    startActivity(start);
                } else if ((Email.getText().toString().equals("s")) && (Password.getText().toString().equals("s"))) {
                    Intent start = new Intent(this, StudentMainActivity.class);
                    startActivity(start);
                }
            }

//            if ((Email.getText().toString().equals("c")) && (Password.getText().toString().equals("c"))) {
//                Intent start = new Intent(this, Course_manager_home.class);
//                startActivity(start);
//            } else if ((Email.getText().toString().equals("t")) && (Password.getText().toString().equals("t"))) {
//                Intent start = new Intent(this, TeacherMainActivity.class);
//                startActivity(start);
//            } else if ((Email.getText().toString().equals("ch")) && (Password.getText().toString().equals("ch"))) {
//                Intent start = new Intent(this, ComplainMain.class);
//                startActivity(start);
//            } else if ((Email.getText().toString().equals("s")) && (Password.getText().toString().equals("s"))) {
//                Intent start = new Intent(this, StudentMainActivity.class);
//                startActivity(start);
//            }
        });
    }
/*    public void gotoTmPdf(View view) {
        final Context context = this;
        Intent intent = new Intent(context, Register.class);
        startActivity(intent);
    }*/
}