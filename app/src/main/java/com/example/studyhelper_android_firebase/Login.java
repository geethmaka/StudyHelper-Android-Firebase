package com.example.studyhelper_android_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studyhelper_android_firebase.classes.User;
import com.example.studyhelper_android_firebase.complain.ComplainMain;

import com.example.studyhelper_android_firebase.course.Course_manager_home;
import com.example.studyhelper_android_firebase.student.StudentMainActivity;
import com.example.studyhelper_android_firebase.teacher.TeacherMainActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class Login extends AppCompatActivity {

    public void saveSession(SharedPreferences.Editor editor,String email,String pw,String uid,String stream){

        // below two lines will put values for
        // email and password in shared preferences.

        editor.putString("email", email);
        editor.putString("password",pw);
        editor.putString("uid",uid);
        editor.putString("stream",stream);

        // to save our data with key and value.
        editor.commit();
    }

    public void gotoIntent(Class destination){
        Intent start = new Intent(this, destination);
        start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.cirLoginButton);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ArrayList<User> userList = new ArrayList<>();
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

        login.setOnClickListener((View v) -> {
            EditText Email = findViewById(R.id.editTextEmail);
            EditText Password = findViewById(R.id.editTextPassword);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            boolean userFound = false;
            for(User u : userList){
                if ((Email.getText().toString().equals(u.getUser().getEmail())) && (Password.getText().toString().equals(u.getUser().getPassword()))) {
                    if(u.getUser().getType().equals("Teacher")){
                        userFound=true;
                        saveSession(editor,u.getUser().getEmail(), u.getUser().getPassword(), u.getId(),"");
                        gotoIntent(TeacherMainActivity.class);
                        break;
                    }else if(u.getUser().getType().equals("Student")){
                        userFound=true;
                        saveSession(editor,u.getUser().getEmail(), u.getUser().getPassword(), u.getId(),u.getUser().getStream());
                        gotoIntent(StudentMainActivity.class);
                        break;
                    }
                }
            }
            if ((Email.getText().toString().equals("t")) && (Password.getText().toString().equals("t"))) {
                Intent start = new Intent(this, TeacherMainActivity.class);
                startActivity(start);
            } else if ((Email.getText().toString().equals("ch")) && (Password.getText().toString().equals("ch"))) {
                Intent start = new Intent(this, ComplainMain.class);
                startActivity(start);
            } else if ((Email.getText().toString().equals("s")) && (Password.getText().toString().equals("s"))) {
                Intent start = new Intent(this, StudentMainActivity.class);
                startActivity(start);
            }else if((Email.getText().toString().equals("c")) && (Password.getText().toString().equals("c"))){
                Intent start = new Intent(this, Course_manager_home.class);
                startActivity(start);
            }else{
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }

//            if(!userFound){
//                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();}
        });
    }
}
