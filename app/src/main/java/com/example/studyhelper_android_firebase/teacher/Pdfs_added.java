package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Link;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pdfs_added#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pdfs_added extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pdfs_added() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pdfs_added.
     */
    // TODO: Rename and change types and number of parameters
    public static Pdfs_added newInstance(String param1, String param2) {
        Pdfs_added fragment = new Pdfs_added();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    ArrayList<Pdf> pdfArrayList;
    PdfAdapter pdfAdapter;
    String[] subject;
    String[] title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view;


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


         }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pdfs_added, container, false);

        Context current=this.getContext();
        recyclerView=root.findViewById(R.id.uploadPdfRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(current));

        pdfArrayList=new ArrayList<Pdf>();
        pdfAdapter= new PdfAdapter(pdfArrayList, this.getContext());
        recyclerView.setAdapter(pdfAdapter);
        EventChangeListener();

        return root;



    }

    private void EventChangeListener() {
        db.collection("pdf")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null) {
                            //dismiss progress dialog
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }

                        //fetching the data from the firestore database
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED) {
                                pdfArrayList.add(dc.getDocument().toObject(Pdf.class));
                            }
                            pdfAdapter.notifyDataSetChanged();
                            //dismiss progress dialog
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
                        }

                    }
                });
    }
}

