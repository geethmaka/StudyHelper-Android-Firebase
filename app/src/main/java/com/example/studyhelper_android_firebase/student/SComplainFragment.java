package com.example.studyhelper_android_firebase.student;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.studyhelper_android_firebase.ComTest;
import com.example.studyhelper_android_firebase.R;
import android.util.Log;
import android.widget.Toast;

import com.example.studyhelper_android_firebase.classes.IComplain;
import com.example.studyhelper_android_firebase.complain.NewComplaint;
import com.example.studyhelper_android_firebase.teacher.T_complains;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    //initialize/ variables

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
        Context mContext = getContext();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ComTest isnull = new ComTest();
        Button uploadButton = root.findViewById(R.id.scomplaintadd);

        Spinner cType =  root.findViewById(R.id.stype);
        //get the content of the message
        EditText message = root.findViewById(R.id.smassage);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.format(currentTime);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        String id =preferences.getString("uid","");

        //inserting the complaint to the database
        uploadButton.setOnClickListener((View v) -> {
            String content = String.valueOf(message.getText());
            //if the content is null is the message
            if(!isnull.nullContent(content)){
                Toast.makeText(mContext,"Please Enter a complaint",Toast.LENGTH_LONG).show();
            }
            else {
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
