package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.studyhelper_android_firebase.ComTest;
import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.IComplain;
import com.example.studyhelper_android_firebase.services.Services;
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
        Context mContext = getContext();
        Services services = new Services();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ComTest isnull = new ComTest();
        Button submit = root.findViewById(R.id.scomplaintadd);
        Button scomplains = root.findViewById(R.id.scomplains);

        //getting data from the edit text and the spinner
        Spinner cType = root.findViewById(R.id.stype);
        EditText message = root.findViewById(R.id.smessage);

        //getting the system data
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.format(currentTime);

        //getting the user id and email from the shared preference
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        String id = preferences.getString("uid", "");
        String email = preferences.getString("email", "");
        String type = cType.getSelectedItem().toString();
//        String content = message.getText().toString();
        String date = sdf.format(currentTime).toString();


        //add a complain to the system
        submit.setOnClickListener((View v) -> {
            String content = message.getText().toString();
            System.out.println(content);
            //if message content is null
            if (!isnull.nullContent(content)) {
                Toast.makeText(mContext, "Please Enter a complaint message", Toast.LENGTH_LONG).show();
            } else {
                IComplain c = new IComplain(id, type, "Pending", date, content);
                //prompting an confirmation dialog box
                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                alertDialog.setTitle("Complain confirmation");
                alertDialog.setMessage("Do you want to Add the Complain");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog, ID) ->
                        //add the complain to the database
                        db.collection("complain")
                                .add(c)
                                .addOnSuccessListener(documentReference -> {
                                    //sending confirmation email
                                    String body = "Hello, \nWe have received your complaint successfully.Our team will resolve your problem as soon as possible. Thank you for keeping trust on us.\n\nThankyou,\nStudy-Helper.";
                                    services.sendMail(email, "Study-Helper Complaint Confirmation", body);
                                    //successfull message
                                    Toast.makeText(mContext, "Complaint Added Successfully!!!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(v.getContext(), Student_complaint.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                })
                                .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e)));
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialog, id1) -> dialog.dismiss());
                alertDialog.show();
            }
        });

        //go to Added_complain_T
        scomplains.setOnClickListener(view -> {
            Intent i = new Intent(mContext, Student_complaint.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });

        return root;
    }
}