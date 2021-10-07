package com.example.studyhelper_android_firebase.student;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SComplainFragment extends Fragment {

    public SComplainFragment() {
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
        View root = inflater.inflate(R.layout.fragment_s_complain, container, false);
        Context mContext = getContext();
        Services services = new Services();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ComTest isnull = new ComTest();
        Button uploadButton = root.findViewById(R.id.scomplaintadd);

        Spinner cType = root.findViewById(R.id.stype);
        //get the content of the message
        EditText message = root.findViewById(R.id.smassage);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.format(currentTime);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        String id = preferences.getString("uid", "");
        String email = preferences.getString("email", "");

        //inserting the complaint to the database
        uploadButton.setOnClickListener((View v) -> {
            String content = String.valueOf(message.getText());
            //if the content is null is the message
            if (!isnull.nullContent(content)) {
                Toast.makeText(mContext, "Please Enter a complaint", Toast.LENGTH_LONG).show();
            } else {
                IComplain c = new IComplain(id, cType.getSelectedItem().toString(), "Pending", sdf.format(currentTime).toString(), message.getText().toString());
                //prompting an confirmation dialog box
                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
                alertDialog.setTitle("Delete User confirmation");
                alertDialog.setMessage("Do you want to Delete the User");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog, ID) ->
                        //adding the complaint to the database
                        db.collection("complain")
                                .add(c)
                                .addOnSuccessListener(documentReference -> {
                                    //sending confirmation email
                                    String body = "Hello, \nWe have received your complaint successfully.Our team will resolve your problem as soon as possible. Thank you for keeping trust on us.\n\nThankyou,\nStudy-Helper.";
                                    services.sendMail(email, "Study-Helper Complaint Confirmation", body);

                                    Toast.makeText(root.getContext().getApplicationContext(), "Complaint Added Successfully!!!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(v.getContext(), Student_complaint.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    v.getContext().startActivity(i);
                                })
                                .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e)));
                //if "NO" is selected
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialog, id1) -> dialog.dismiss());
                alertDialog.show();
            }
        });

        //redirect to scomplain activity
        Button scomplains = root.findViewById(R.id.scomplains);
        scomplains.setOnClickListener(view -> {
            Intent i = new Intent(mContext, Student_complaint.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });

        return root;
    }

}
