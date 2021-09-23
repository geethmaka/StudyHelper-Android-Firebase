package com.example.studyhelper_android_firebase.complain;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class InactiveUsers extends AppCompatActivity {

    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //defining the variables
    ProgressDialog progressDialog;
    ArrayList<User> userArrayList;
    Adapter_inactiveUsers userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_inactive_users);
        //getting the current context

        //creating progress dialog until fetching the data
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching the Data...");
        progressDialog.show();

        //defining the variables
        RecyclerView recyclerView = findViewById(R.id.RVinactiveUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initialize the array list
        userArrayList = new ArrayList<User>();
        //initialize the adapter
        userAdapter = new Adapter_inactiveUsers(this,userArrayList);
        recyclerView.setAdapter(userAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("users").orderBy("username", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null) {
                            //dismiss progress dialog
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }

                        //fetching the data from the firestore database
                        for(DocumentChange dc : value.getDocumentChanges()){
                            User n = new User(dc.getDocument().getId(), dc.getDocument().toObject(User.class));
                            if(dc.getType() == DocumentChange.Type.ADDED && n.getUser().getStatus().equals("inactive")) {
                                    userArrayList.add(n);
                            }
                            userAdapter.notifyDataSetChanged();
                            //dismiss progress dialog
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                    }
                });
    }
}