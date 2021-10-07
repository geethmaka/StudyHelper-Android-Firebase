package com.example.studyhelper_android_firebase.complain;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class CH_Dashboard extends Fragment {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //defining the variables
    ArrayList<Complain> complainArrayList;
    Adapter_Dash dashAdapter;
    RecyclerView recyclerView;

    ProgressDialog progressDialog;
    ProgressBar pd_pending;
    ProgressBar pd_resolved;
    TextView per_pending;
    TextView per_resolved;


    public CH_Dashboard() {
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
        View root = inflater.inflate(R.layout.fragment_ch_dashboard, container, false);
        //get the current context
        Context current = this.getContext();
        showprogress();

        Cal cal = new Cal();

        /*
        displaying the overview
         */
        //assigning the progress bars
        pd_pending = root.findViewById(R.id.PB_pending);
        pd_resolved = root.findViewById(R.id.PB_resolved);
        //assigning the text views
        per_pending = root.findViewById(R.id.tv_pending_perc);
        per_resolved = root.findViewById(R.id.tv_resolved_perc);

        db.collection("complain")
                .get()
                .addOnCompleteListener(task -> {
                    int total = 0;
                    if (task.isSuccessful()) {
                        for(QueryDocumentSnapshot dc : task.getResult()){
                            Complain c = dc.toObject(Complain.class);
                            total ++;
                        }
                        String stringtot = String.valueOf(total);

                        db.collection("complain")
                                .get()
                                .addOnCompleteListener(task1 -> {
                                    int pending = 0;
                                    if (task.isSuccessful()) {
                                        for(QueryDocumentSnapshot dc : task.getResult()){
                                            Complain c = dc.toObject(Complain.class);
                                            if (c.getStatus().equals("Pending")) {
                                                pending ++;
                                            }
                                        }
                                        //taking the pending percentage
                                        int finalTotal = Integer.parseInt(stringtot);

                                        //getting the percentage value
                                        int p_per = cal.getPercentage(pending,finalTotal);

                                        per_pending.setText(String.valueOf(p_per));
                                        pd_pending.setProgress(p_per);
                                    } else {
                                        Log.d("TAG", "Error getting documents: ", task1.getException());
                                    }
                                });

                        db.collection("complain")
                                .get()
                                .addOnCompleteListener(task2 -> {
                                    int resolved = 0;
                                    if (task.isSuccessful()) {
                                        for(QueryDocumentSnapshot dc : task.getResult()){
                                            Complain c = dc.toObject(Complain.class);
                                            if (c.getStatus().equals("Resolved")) {
                                                resolved ++;
                                            }
                                        }
                                        //getting the resolved complaint percentage
                                        int finalTotal = Integer.parseInt(stringtot);

                                        //getting the percentage value
                                        int r_per = cal.getPercentage(resolved,finalTotal);

                                        per_resolved.setText(String.valueOf(r_per));
                                        pd_resolved.setProgress(r_per);
                                    } else {
                                        Log.d("TAG", "Error getting documents: ", task2.getException());
                                    }
                                });

                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                });


        //defining the variables;
        recyclerView = root.findViewById(R.id.RVcomplain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(current, RecyclerView.HORIZONTAL, false));
        //initialize the array list
        complainArrayList = new ArrayList<Complain>();
        //initialize the adapter
        dashAdapter = new Adapter_Dash(this.getContext(), complainArrayList);
        recyclerView.setAdapter(dashAdapter);

        EventChangeListener();
        return root;
    }

    //getting data from the database
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
                        Complain c = new Complain(dc.getDocument().getId(), dc.getDocument().toObject(Complain.class));

                        if (dc.getType() == DocumentChange.Type.ADDED && c.getComplain().getStatus().equals("Pending")) {
                            complainArrayList.add(c);
                        }
                        dashAdapter.notifyDataSetChanged();
                        //dismiss progress dialog
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }

    private void showprogress() {
        //creating progress dialog until fetching the data
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching the Data...");
        progressDialog.show();
    }

}