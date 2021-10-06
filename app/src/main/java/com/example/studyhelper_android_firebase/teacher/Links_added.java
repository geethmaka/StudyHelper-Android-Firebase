package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Link;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Links_added#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Links_added extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Links_added() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment links_added.
     */
    // TODO: Rename and change types and number of parameters
    public static Links_added newInstance(String param1, String param2) {
        Links_added fragment = new Links_added();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    ArrayList<Link> linkArrayList;
    LinkAdapter linkAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_links_added, container, false);

        Context current=this.getContext();
        recyclerView=root.findViewById(R.id.uploadLinkRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(current));

        linkArrayList= new ArrayList<Link>();
        linkAdapter= new LinkAdapter(linkArrayList, this.getContext());
        recyclerView.setAdapter(linkAdapter);
        EventChangeListener();

        return root;



    }

    private void EventChangeListener() {
        db.collection("link")
                .addSnapshotListener((value, error) -> {
                    if(error != null) {
                        //dismiss progress dialog
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
                        Log.e("Firestore Error",error.getMessage());
                        return;
                    }

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                    String id =preferences.getString("uid","");

                    //fetching the data from the firestore database
                    for(DocumentChange dc : value.getDocumentChanges()){
                        Link l = new Link(dc.getDocument().getId(),dc.getDocument().toObject(Link.class));
                        if(dc.getType() == DocumentChange.Type.ADDED && id.equals(l.getObj().getTid())) {
                            linkArrayList.add(l);
                        }
                        linkAdapter.notifyDataSetChanged();
                        //dismiss progress dialog
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
                    }

                });
    }
}

