package com.example.studyhelper_android_firebase.course;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.ICourse;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourses.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourses newInstance(String param1, String param2) {
        AddCourses fragment = new AddCourses();
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

        View root = inflater.inflate(R.layout.fragment_add_courses, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button addButton = root.findViewById(R.id.addButton);
        Spinner stream = root.findViewById(R.id.addStream);

        addButton.setOnClickListener((View v) -> {

            EditText subject = root.findViewById(R.id.addSubject);
            Switch availability = root.findViewById(R.id.addAvailability);


            ICourse c = new ICourse(subject.getText().toString(), stream.getSelectedItem().toString(), availability.isChecked());

            db.collection("courses")
                    .add(c)
                    .addOnSuccessListener(documentReference -> Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e));
        });

        return root;
    }
}