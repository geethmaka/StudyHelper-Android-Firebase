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
import com.example.studyhelper_android_firebase.student.Student_complaint;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class T_complains extends Fragment {

    public T_complains() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            Spinner cType = root.findViewById(R.id.stype);
            EditText message = root.findViewById(R.id.smessage);
            //getting the system data
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            sdf.format(currentTime);
            //getting the user id from the shared preference
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
            String id = preferences.getString("uid", "l");

            IComplain c = new IComplain(id, cType.getSelectedItem().toString(), "Pending", sdf.format(currentTime).toString(), message.getText().toString());

            //add the complain to the database
            db.collection("complain")
                    .add(c)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(root.getContext().getApplicationContext(), "Complaint Added Successfully!!!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(v.getContext(), T_complains.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    })

                    .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e));
        });

        Context mContext = getContext();
        Button scomplains = root.findViewById(R.id.scomplains);

        //go to Added_complain_T
        scomplains.setOnClickListener(view -> {
            Intent i = new Intent(mContext, Student_complaint.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });

        return root;
    }
}