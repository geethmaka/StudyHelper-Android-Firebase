package com.example.studyhelper_android_firebase.complain;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        /*
        displaying the overview
         */
        //assigning the progress bars
        pd_pending = root.findViewById(R.id.PB_pending);
        pd_resolved = root.findViewById(R.id.PB_resolved);
        //assigning the text views
        per_pending = root.findViewById(R.id.tv_pending_perc);
        per_resolved = root.findViewById(R.id.tv_resolved_perc);

//        pd_pending.setProgress(20);
//        pd_resolved.setProgress(100);

//        int pending = getCount("Pending");
//        int resolved = getCount("Resolved");
//        getCount("Resolved");
//        getCount("Pending");
//        Log.d("count", String.valueOf(getCount("Pending")));
//        Log.d("count", String.valueOf(getCount("Resolved")));

        db.collection("complain")
                .get()
                .addOnCompleteListener(task -> {
                    int total = 0;
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot dc : task.getResult()) {
                            Complain c = dc.toObject(Complain.class);
                            total++;
                        }
                        Log.d("total", String.valueOf(total));
                        String stringtot = String.valueOf(total);

                        db.collection("complain")
                                .get()
                                .addOnCompleteListener(task1 -> {
                                    int pending = 0;
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot dc : task.getResult()) {
                                            Complain c = dc.toObject(Complain.class);
                                            if (c.getStatus().equals("Pending")) {
                                                pending++;
                                            }
                                        }
                                        //taking the pending percentage
                                        int finalTotal = Integer.parseInt(stringtot);
                                        int p_per =  Math.round((float)pending / finalTotal*100);
                                        per_pending.setText(String.valueOf(p_per));
                                        Log.d("per",String.valueOf(p_per));
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
                                        for (QueryDocumentSnapshot dc : task.getResult()) {
                                            Complain c = dc.toObject(Complain.class);
                                            if (c.getStatus().equals("Resolved")) {
                                                resolved++;
                                            }
                                        }
                                        //getting the resolved complaint percentage
                                        int finalTotal = Integer.parseInt(stringtot);
                                        int r_per =  Math.round((float)resolved / finalTotal*100);
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

    //getting the count of
//    private int getCount(String type) {
//        //getting the count from the database
//        db.collection("complain")
//                .get()
//                .continueWith(new Continuation<QuerySnapshot, Integer>() {
//                    @Override
//                    public Integer then(@NonNull Task<QuerySnapshot> task) throws Exception {
//                        int count = 0;
//                        for (DocumentSnapshot dc : task.getResult()) {
//                            Complain c = dc.toObject(Complain.class);
//                            if (c.getStatus().equals(type)) {
//                                count ++;
//                            }
//                        }
////                        Log.d("count", String.valueOf(count));
////                        String name = ((Object)count).getClass().getName();
////                        Log.d("count", String.valueOf(count));
////                        Log.d("count", name);
//                        return count;
//                    }
//                });
//        return 0;
//    }
}


//    private int getCount(String type) {
//        AtomicInteger count = new AtomicInteger();
//        int result = 0;
//        //getting the count from the database
//        db.collection("complain")
//                .addSnapshotListener((value, error) -> {
//                    if(error != null) {
//                        Log.e("Firestore Error",error.getMessage());
//                        return;
//                    }
//                    //reading data from the firestore database
//                    for(DocumentChange dc : value.getDocumentChanges()){
//                        Complain c = new Complain(dc.getDocument().getId(),dc.getDocument().toObject(Complain.class));
//                        if(dc.getType() == DocumentChange.Type.ADDED && c.getComplain().getStatus().equals(type)) {
//                            complainArrayList.add(c);
//                        }
//                    }
//                    count.set(complainArrayList.size());
//                });
//        result = count.intValue();
//        return result;
//    }