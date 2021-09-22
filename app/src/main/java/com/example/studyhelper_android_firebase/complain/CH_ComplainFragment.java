package com.example.studyhelper_android_firebase.complain;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CH_ComplainFragment extends Fragment {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //defining the variables
    ProgressDialog progressDialog;
    ArrayList<Complain> complainArrayList;
    Adapter_Complain complainAdapter;

    public CH_ComplainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ch_complain, container, false);
        //get the current context
        Context current = this.getContext();

        //creating progress dialog until fetching the data
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching the Data...");
        progressDialog.show();

        //defining the variables;
        RecyclerView recyclerView = root.findViewById(R.id.RVcomplain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(current));
        //initialize the array list
        complainArrayList = new ArrayList<Complain>();
        //initialize the adapter
        complainAdapter = new Adapter_Complain(this.getContext(),complainArrayList);
        recyclerView.setAdapter(complainAdapter);

        EventChangeListener();
        return root;
    }

    private void EventChangeListener() {
        db.collection("complain").orderBy("date", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    if(error != null) {
                        //dismiss progress dialog
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore Error",error.getMessage());
                        return;
                    }

                    //fetching the data from the firestore database
                    for(DocumentChange dc : value.getDocumentChanges()){
                        if(dc.getType() == DocumentChange.Type.ADDED) {
                            Complain c = new Complain(dc.getDocument().getId(),dc.getDocument().toObject(Complain.class));
                            complainArrayList.add(c);
                        }
                        complainAdapter.notifyDataSetChanged();
                        //dismiss progress dialog
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                    }

                });
    }
}