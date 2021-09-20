package com.example.studyhelper_android_firebase.course;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewCourses extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewCourses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewCourses.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewCourses newInstance(String param1, String param2) {
        ViewCourses fragment = new ViewCourses();
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Course> coursesL = new LinkedList<>();

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view_courses, container, false);
        ArrayList<String> courses = new ArrayList<>();
        Context currentContext = this.getContext();

        RecyclerView recyclerView = root.findViewById(R.id.recycleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(currentContext));

        db.collection("courses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Course course=document.toObject(Course.class);
                                coursesL.add(course);
                                courses.add(course.getSubject());
                            }
                            RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(courses,requireActivity().getApplicationContext());
                            recyclerView.setAdapter(mAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }

                });

        return root;
    }
}