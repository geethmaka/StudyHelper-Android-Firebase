package com.example.studyhelper_android_firebase.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;
import com.example.studyhelper_android_firebase.teacher.T_dashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SProfileFragment newInstance(String param1, String param2) {
        SProfileFragment fragment = new SProfileFragment();
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

        View root = inflater.inflate(R.layout.fragment_s_profile, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button update = root.findViewById(R.id.supdate);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        String id =preferences.getString("uid","");

        EditText username = root.findViewById(R.id.sname);
        EditText mobile = root.findViewById(R.id.smn);
        EditText  email = root.findViewById(R.id.semail);
        EditText  stream = root.findViewById(R.id.sstream);

        //showing the data in the profile
        DocumentReference docRef = db.collection("users").document(id);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    User u = document.toObject(User.class);
                    username.setText(u.getUsername());
                    mobile.setText(Long.toString(u.getMobile()));
                    stream.setText(u.getStream());
                    email.setText(u.getEmail());
                } else {
                    Log.d("TAG", "No such document");
                }
            } else {
                Log.d("TAG", "get failed with ", task.getException());
            }
        });

        //updating the profile details
        update.setOnClickListener((View v) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
            alertDialog.setTitle("Update");
            alertDialog.setMessage("Are you sure you want to update this link");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog, ID) -> db.collection("users").document(id)
                        .update("username", username.getText().toString(), "mobile", mobile.getText(), "email",email.getText().toString()
                            ,"stream",stream.getText().toString())
                    .addOnSuccessListener(aVoid -> {
                        Log.d("TAG", "DocumentSnapshot successfully updated!" + id);
                        Intent i = new Intent(v.getContext(), SProfileFragment.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(i);
                    })
                    .addOnFailureListener(e -> {
                    }));
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialog, id1) -> dialog.dismiss());
            alertDialog.show();
        });

        return  root;
    }
}