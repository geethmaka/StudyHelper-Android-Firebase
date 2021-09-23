package com.example.studyhelper_android_firebase.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class Student_complaint extends AppCompatActivity {

    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //defining the variables
    ArrayList<Complain> complainArrayList;
    S_adptercomplain complainAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_complaint);

        //creating progress dialog until fetching the data


        //defining the variables;
        RecyclerView recyclerView = findViewById(R.id.srecycalviwe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initialize the array list
        complainArrayList = new ArrayList<Complain>();
        //initialize the adapter
        complainAdapter = new S_adptercomplain(this,complainArrayList);
        recyclerView.setAdapter(complainAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("complain")
                .addSnapshotListener((value, error) -> {
                    if(error != null) {
                        //dismiss progress dialog
                        Log.e("Firestore Error",error.getMessage());
                        return;
                    }
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String id =preferences.getString("uid","");

                    //fetching the data from the firestore database
                    for(DocumentChange dc : value.getDocumentChanges()){
<<<<<<< HEAD
                        if(dc.getType() == DocumentChange.Type.ADDED ) {
                            Complain c = new Complain(dc.getDocument().getId(),dc.getDocument().toObject(Complain.class));
=======
                        Complain c = new Complain(dc.getDocument().getId(),dc.getDocument().toObject(Complain.class));
                        if(dc.getType() == DocumentChange.Type.ADDED && c.getComplain().getUserID().equals(id)) {
>>>>>>> d99e34b2d875012d166a431991b30c60e6d9b9c4
                            complainArrayList.add(c);
                        }
                        complainAdapter.notifyDataSetChanged();
                        //dismiss progress dialog

                    }

                });
    }
}