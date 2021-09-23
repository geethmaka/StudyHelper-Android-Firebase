package com.example.studyhelper_android_firebase.student;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class S_adptercomplain extends RecyclerView.Adapter<S_adptercomplain.HolderComplain>{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ArrayList<Complain> complainList;

    public S_adptercomplain(Context context, ArrayList<Complain> complainList) {
        this.context = context;
        this.complainList = complainList;
    }

    @NonNull
    @Override
    public S_adptercomplain.HolderComplain onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sitem,parent,false);
        return new HolderComplain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull S_adptercomplain.HolderComplain holder, int position) {
        // get data
        Complain cp= complainList.get(position);


       holder.scvstatus.setText(cp.getComplain().getStatus());
       holder.scvdate.setText(cp.getComplain().getDate());
       holder.scvmassage.setText(cp.getComplain().getContent());

//        holder.scvupdate.setOnClickListener(v -> {
//
//            DocumentReference complainRef = db.collection("complain").document(complain.getComplainId());
//
//            complainRef.update("Status", "Resolved")
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d("TAG", "The complain is marked resolved successfully!");
//                        }
//                    })
//
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w("TAG", "Error updating status", e);
//                        }
//                    });
//        });

    }


    @Override
    public int getItemCount() {
        return complainList.size();
    }

    static class HolderComplain extends RecyclerView.ViewHolder{
        //holds views of recyclerview

        TextView scvdate,scvstatus;
        EditText scvmassage;

        public HolderComplain(@NonNull View itemView) {
            super(itemView);

            scvdate= itemView.findViewById(R.id.scvdate);
            scvmassage= itemView.findViewById(R.id.scvmassage);
            scvstatus=itemView.findViewById(R.id.scvstatus);

        }

    }

}




