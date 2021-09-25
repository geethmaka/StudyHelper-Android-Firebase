package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pdfs_added#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pdfs_added extends Fragment {


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
    Button update;
    EditText title, pdf;

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

//        update= (Button) root.findViewById(update);
        Context current = this.getContext();
        recyclerView = root.findViewById(R.id.uploadPdfRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(current));

        pdfArrayList = new ArrayList<Pdf>();
        pdfAdapter = new PdfAdapter(pdfArrayList, this.getContext());
        recyclerView.setAdapter(pdfAdapter);
        EventChangeListener();

        return root;


    }

    private void EventChangeListener() {
        db.collection("pdf")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
//                        dismiss progress dialog
//                           if(progressDialog.isShowing())
//                                progressDialog.dismiss();
                        Log.e("Firestore Error", error.getMessage());
                        return;
                    }
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String id = preferences.getString("uid", "");
                    //fetching the data from the firestore database
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        Pdf p = new Pdf(dc.getDocument().getId(), dc.getDocument().toObject(Pdf.class));

                        if (dc.getType() == DocumentChange.Type.ADDED && id.equals(p.getObj().getTid())) {
                            pdfArrayList.add(p);
                        }
                        pdfAdapter.notifyDataSetChanged();
                        //dismiss progress dialog
//                          if(progressDialog.isShowing())
//                           progressDialog.dismiss();
                    }

                });
    }
}

