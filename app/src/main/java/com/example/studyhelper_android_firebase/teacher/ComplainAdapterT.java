package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ComplainAdapterT extends RecyclerView.Adapter<ComplainAdapterT.ViewHolder> {
    //creating an instance of the database

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ArrayList<Complain> complainArrayList;

    public ComplainAdapterT(Context context, ArrayList<Complain> newArrayList) {
        this.context = context;
        this.complainArrayList = newArrayList;
    }

    @NonNull
    @Override
    public ComplainAdapterT.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_resolved, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplainAdapterT.ViewHolder holder, int position) {
        Complain complain = complainArrayList.get(position);

        //getting the username from the database giving the userid in complain
        db.collection("complain")
                .document(complain.getComplainId())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Complain c = document.toObject(Complain.class);
                            db.collection("users")
                                    .document(c.getUserID())
                                    .get()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            DocumentSnapshot doc = task1.getResult();
                                            if (doc.exists()) {
                                                User u = doc.toObject(User.class);
                                                //setting the username
                                                holder.username.setText(u.getUsername());
                                            } else {
                                                Log.d("TAG", "No such document");
                                            }
                                        } else {
                                            Log.d("TAG", "get failed with ", task.getException());
                                        }
                                    });
                        } else {
                            Log.d("TAG", "No such document");
                        }
                    } else {
                        Log.d("TAG", "get failed with ", task.getException());
                    }
                });

        holder.status.setText(complain.getComplain().getStatus());
        holder.complain.setText(complain.getComplain().getContent());
    }

    @Override
    public int getItemCount() {
        return complainArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView status;
        TextView complain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_complain_name);
            status = itemView.findViewById(R.id.tv_status);
            complain = itemView.findViewById(R.id.user_complain);
        }
    }
}