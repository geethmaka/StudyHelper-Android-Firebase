package com.example.studyhelper_android_firebase.complain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;

import java.util.ArrayList;

public class CH_UserDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    ArrayList<User> newArrayList;
    Adapter_UserDetails userAdapter;
    String[] username;
    String[] type;
    String[] email;
    Button banUser;

    public CH_UserDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = root.findViewById();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ch_user_details, container, false);
    }
}