package com.example.studyhelper_android_firebase.complain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studyhelper_android_firebase.R;


public class CH_ComplainMenu extends Fragment {

    public CH_ComplainMenu() {
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
        View root = inflater.inflate(R.layout.fragment_ch_complain, container, false);

        Context mContext = getContext();

        Button btn_cPending = root.findViewById(R.id.btn_cPending);
        Button btn_active = root.findViewById(R.id.btn_Active_User);
        Button btn_inactive = root.findViewById(R.id.btn_Inactive_user);
        Button btn_cResolved = root.findViewById(R.id.btn_cResolved);

        btn_cPending.setOnClickListener(view -> {
            Intent i = new Intent(mContext, NewComplaint.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });

        btn_active.setOnClickListener(view -> {
            Intent i = new Intent(mContext, ActiveUsers.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });

        btn_inactive.setOnClickListener(view -> {
            Intent i = new Intent(mContext, InactiveUsers.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });

        btn_cResolved.setOnClickListener(view -> {
            Intent i = new Intent(mContext, ResolvedComplaints.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        });
        return root;
    }
}