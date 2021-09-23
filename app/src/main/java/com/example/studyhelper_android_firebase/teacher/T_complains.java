package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.IComplain;
import com.example.studyhelper_android_firebase.complain.InactiveUsers;
import com.example.studyhelper_android_firebase.student.Student_complaint;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link T_complains#newInstance} factory method to
 * create an instance of this fragment.
 */
public class T_complains extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public T_complains() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment t_complains.
     */
    // TODO: Rename and change types and number of parameters
    public static T_complains newInstance(String param1, String param2) {
        T_complains fragment = new T_complains();
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
        View root = inflater.inflate(R.layout.fragment_t_complains, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button uploadButton = root.findViewById(R.id.scomplaintadd);

        //add a complain to the system
        uploadButton.setOnClickListener((View v) -> {
            //getting data from the edit text and the spinner
            Spinner cType =  root.findViewById(R.id.stype);
            EditText message = root.findViewById(R.id.smessage);
            //getting the system data
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            sdf.format(currentTime);
            //getting the user id from the shared preference
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
            String id =preferences.getString("uid","l");

            IComplain c =new IComplain(id,cType.getSelectedItem().toString(),"Pending",sdf.format(currentTime).toString(),message.getText().toString());

            db.collection("complain")
                    .add(c)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(root.getContext().getApplicationContext(), "Complaint Added Successfully!!!",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(v.getContext(), T_complains.class);
                        startActivity(i);
                    })

                    .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e));
        });

        Context mContext = getContext();
        Button scomplains = root.findViewById(R.id.scomplains);

        scomplains.setOnClickListener(view -> {
            Intent i = new Intent(mContext, Student_complaint.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });

        return root;
    }
}