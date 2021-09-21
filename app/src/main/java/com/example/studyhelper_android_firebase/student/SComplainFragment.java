package com.example.studyhelper_android_firebase.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.studyhelper_android_firebase.R;

import androidx.annotation.NonNull;
import android.util.Log;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SComplainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class SComplainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //inisialize/ variables

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SComplainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SComplainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SComplainFragment newInstance(String param1, String param2) {
        SComplainFragment fragment = new SComplainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_s_complain, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button uploadButton = root.findViewById(R.id.scomplaintadd);

        uploadButton.setOnClickListener((View v) -> {

            Spinner cType =  root.findViewById(R.id.stype);
            CalendarView date = root.findViewById(R.id.scalendarView);
            EditText massage = root.findViewById(R.id.smassage);


           Complain complain =new Complain(cType.getSelectedItem().toString(),date.getDate(),massage.getText().toString());

            db.collection("complain")
                    .add(complain)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                        }
                    });
        });
        return root;
    }
}
