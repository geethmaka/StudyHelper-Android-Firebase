package com.example.studyhelper_android_firebase.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.example.studyhelper_android_firebase.complain.Adapter_newComplaint;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class Added_complain_T extends AppCompatActivity {

    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //defining the variables
    ProgressDialog progressDialog;
    ArrayList<Complain> complainArrayList;
    Adapter_newComplaint complainAdapter;
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    String id = preferences.getString("uid", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_added_complain);

        //creating progress dialog until fetching the data
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching the Data...");
        progressDialog.show();

        //defining the variables;
        RecyclerView recyclerView = findViewById(R.id.RVcomplainT);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initialize the array list
        complainArrayList = new ArrayList<Complain>();
        //initialize the adapter
        complainAdapter = new Adapter_newComplaint(this, complainArrayList);
        recyclerView.setAdapter(complainAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("complain")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        //dismiss progress dialog
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore Error", error.getMessage());
                        return;
                    }
                    //fetching the data from the firestore database
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            Complain c = new Complain(dc.getDocument().getId(), dc.getDocument().toObject(Complain.class));
                            if (c.getComplain().getUserID().equals(id)) {
                                complainArrayList.add(c);
                            }
                        }
                        complainAdapter.notifyDataSetChanged();
                        //dismiss progress dialog
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }

                });
    }
}