package com.example.studyhelper_android_firebase.student;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.example.studyhelper_android_firebase.course.RecyclerViewAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SDashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SDashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SDashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sdashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SDashboardFragment newInstance(String param1, String param2) {
        SDashboardFragment fragment = new SDashboardFragment();
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
        View root = inflater.inflate(R.layout.fragment_sdashboard, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Pdf> pdfArrayList = new ArrayList<>();
        ArrayList<String> CourseList = new ArrayList<>();
        RecyclerView recyclerView = root.findViewById(R.id.studentPdfRecycler);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String stream = preferences.getString("stream", "");

        db.collection("courses")
                .get().addOnCompleteListener(task -> {
            for (QueryDocumentSnapshot document : task.getResult()) {
                Course c = new Course(document.getId(), document.toObject(Course.class));
                if (c.getCourse().getStream().equals(stream) && c.getCourse().isAvailability()) {
                    String subName = c.getCourse().getSubject();
                    CourseList.add(subName);
                }

            }
            db.collection("pdf")
                    .get()
                    .addOnCompleteListener(t -> {
                        if (t.isSuccessful()) {
                            for (QueryDocumentSnapshot document : t.getResult()) {
                                Pdf p = new Pdf(document.getId(), document.toObject(Pdf.class));

                                if (CourseList.contains(p.getObj().getSubject())) {
                                    pdfArrayList.add(p);
                                }

                            }
                            PdfAdapter mAdapter = new PdfAdapter(pdfArrayList, requireActivity().getApplicationContext());
                            recyclerView.setAdapter(mAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    });
        });
        return root;
    }
}

