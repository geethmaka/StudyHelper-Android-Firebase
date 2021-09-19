package com.example.studyhelper_android_firebase.complain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;

import java.util.ArrayList;

public class Adapter_Complain extends RecyclerView.Adapter<Adapter_Complain.ViewHolder> {

    Context context;
    ArrayList<Complain> newArrayList;

    public Adapter_Complain(Context context, ArrayList<Complain> newArrayList) {
        this.context = context;
        this.newArrayList = newArrayList;
    }

    @NonNull
    @Override
    public Adapter_Complain.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_navcomplain,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Complain.ViewHolder holder, int position) {
        Complain c = newArrayList.get(position);

//        DocumentReference docRef = db.collection("cities").document("SF");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    }
//                    else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });

//        holder.username.setText();
        holder.status.setText(c.status);
        holder.complain.setText(c.complain);
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView status;
        TextView complain;
        Button mark_resolved;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_complain_name);
            status = itemView.findViewById(R.id.tv_status);
            complain = itemView.findViewById(R.id.user_complain);

            itemView.findViewById(R.id.btn_complain_resolve).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
