package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Link;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link T_dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class T_dashboard extends Fragment {
    ArrayList<User> userArrayList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public T_dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment t_dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static T_dashboard newInstance(String param1, String param2) {
        T_dashboard fragment = new T_dashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
    String id =preferences.getString("uid","");
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

        View root = inflater.inflate(R.layout.fragment_t_dashboard, container, false);

        return root;


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EditText username, mobile, email;
        ImageButton update;


        username = root.findViewById(R.id.name);
        mobile = root.findViewById(R.id.mobile);
        email = root.findViewById(R.id.email);
        update = root.findViewById(R.id.update_btn);
        db.collection("users").get( "username","mobile","email")
                .addSnapshotListener((value, error) -> {
                    username.setText(user.getUsername());
                    mobile.setText((int) user.getMobile());
                    email.setText(user.getEmail());

                    update.setOnClickListener(v -> {
                        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
                        alertDialog.setTitle("Update");
                        alertDialog.setMessage("Are you sure you want to update this link");
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog, ID) -> db.collection("user").document(user.getId()).

                                update("username", holder.username.getText().toString(), "mobile", holder.mobile.getText().toString(), "email", holder.email.getText().toString())
                                .addOnSuccessListener(aVoid -> {
                                    Log.d("TAG", "DocumentSnapshot successfully updated!" + user.getId());

                                    holder.username.setText(holder.username.getText().toString());
                                    holder.mobile.setText(holder.mobile.getText().toString());
                                    holder.email.setText(holder.email.getText().toString());
                                })
                                .addOnSuccessListener(aVoid -> {
                                    Intent i = new Intent(v.getContext(), Links_added.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    v.getContext().startActivity(i);
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                }));
                        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }

                        });
                        alertDialog.show();
                    });


//            TextView btn_banUser = itemView.findViewById(R.id.btn_banUser);
                });
    }

}
