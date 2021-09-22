package com.example.studyhelper_android_firebase.complain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_Complain extends RecyclerView.Adapter<Adapter_Complain.ViewHolder> {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ArrayList<Complain> complainArrayList;

    public Adapter_Complain(Context context, ArrayList<Complain> newArrayList) {
        this.context = context;
        this.complainArrayList = newArrayList;
    }

    @NonNull
    @Override
    public Adapter_Complain.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_navcomplain, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Complain.ViewHolder holder, int position) {
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

        holder.btn_cResolve.setOnClickListener(v -> {
            DocumentReference complainRef = db.collection("complain").document(complain.getComplainId());

            complainRef.update("Status", "Resolved")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "The complain is marked resolved successfully!");
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error updating status", e);
                        }
                    });
        });

    }

    @Override
    public int getItemCount() {
        return complainArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView status;
        TextView complain;
        Button btn_cResolve;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_complain_name);
            status = itemView.findViewById(R.id.tv_status);
            complain = itemView.findViewById(R.id.user_complain);
            btn_cResolve = itemView.findViewById(R.id.btn_complain_resolve);

//            itemView.findViewById(R.id.btn_complain_resolve).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }
    }
}
