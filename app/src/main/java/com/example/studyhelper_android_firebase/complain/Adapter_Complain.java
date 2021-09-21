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

        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_navcomplain,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Complain.ViewHolder holder, int position) {
        Complain c = complainArrayList.get(position);

        db.collection("complain")
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Complain c = document.toObject(Complain.class);

                            subject.setText(course.getSubject());
                            availability.setChecked(course.isAvailability());
                        } else {
                            Log.d("TAG", "No such document");
                        }
                    } else {
                        Log.d("TAG", "get failed with ", task.getException());
                    }
                });

        holder.username.setText(c.getUserID());
        holder.status.setText(c.getStatus());
        holder.complain.setText(c.getContent());
    }

    @Override
    public int getItemCount() {
        return complainArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView status;
        TextView complain;
//        Button mark_resolved;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_complain_name);
            status = itemView.findViewById(R.id.tv_status);
            complain = itemView.findViewById(R.id.user_complain);
            TextView btn = itemView.findViewById(R.id.btn_banUser);

//            itemView.findViewById(R.id.btn_complain_resolve).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }
    }
}
