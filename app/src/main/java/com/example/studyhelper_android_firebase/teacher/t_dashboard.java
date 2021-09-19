package com.example.studyhelper_android_firebase.teacher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Link;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link t_dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class t_dashboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public t_dashboard() {
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
    public static t_dashboard newInstance(String param1, String param2) {
        t_dashboard fragment = new t_dashboard();
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

        View root = inflater.inflate(R.layout.link_popup_teacher, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button uploadButton = root.findViewById(R.id.btn_uploadlink);
        uploadButton.setOnClickListener((View v) -> {



            String Subject =  root.findViewById(R.id.spinner).toString();
            String Title = root.findViewById(R.id.link_title).toString();
            View Date = root.findViewById(R.id.calendarView);
            String Time = root.findViewById(R.id.Time).toString();
            String AmPm = root.findViewById(R.id.spinner2).toString();
            String Link = root.findViewById(R.id.link_add).toString();

            Link link=new Link(Subject,Title,Date.toString(),Time,AmPm,Link);

            db.collection("link")
                    .add(link)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                        }
                    });
        });
        return root;



    }
}