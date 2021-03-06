package com.example.studyhelper_android_firebase.complain;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class NewComplaint extends AppCompatActivity {

    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //defining the variables
    ProgressDialog progressDialog;
    ArrayList<Complain> complainArrayList;
    Adapter_newComplaint complainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_new_complain);

        //creating progress dialog until fetching the data
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching the Data...");
        progressDialog.show();

        //defining the variables;
        RecyclerView recyclerView = findViewById(R.id.RVnewComplains);
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
        db.collection("complain").orderBy("date", Query.Direction.DESCENDING)
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
                            if (c.getComplain().getStatus().equals("Pending")) {
                                //adding data from the database to Arraylist
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