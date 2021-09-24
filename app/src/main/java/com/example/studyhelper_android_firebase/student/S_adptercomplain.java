package com.example.studyhelper_android_firebase.student;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    public HolderComplain onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sitem,parent,false);
        return new HolderComplain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull S_adptercomplain.HolderComplain holder, int position) {
        // get data
        Complain cp= complainList.get(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        holder.scvstatus.setText(cp.getComplain().getStatus());
        holder.scvdate.setText(cp.getComplain().getDate());
        holder.scvmassage.setText(cp.getComplain().getContent());

        holder.btn_scvupdate.setOnClickListener(view ->{
            DocumentReference washingtonRef = db.collection("complain").document(cp.getComplainId());

            washingtonRef
                    .update( "content",holder.scvmassage.getText().toString())
                    .addOnSuccessListener(aVoid ->{ Log.d("TAG", "DocumentSnapshot successfully updated!"+cp.getComplainId());
                    Intent i=new Intent(view.getContext(), Student_complaint.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(i);})
                    .addOnFailureListener(e -> Log.w("TAG", "Error updating document", e));
        });

        holder.btn_scvdelete.setOnClickListener((v) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Are you sure you want to delete this complain");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", (dialog, ID) -> db.collection("complain").document(cp.getComplainId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Intent i=new Intent(v.getContext(), Student_complaint.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(i);
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    }));
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"No", (dialog, id) -> dialog.dismiss());
            alertDialog.show();
        });

    }

    @Override
    public int getItemCount() { return complainList.size();}

    static class HolderComplain extends RecyclerView.ViewHolder{

        TextView scvdate,scvstatus;
        EditText scvmassage;
        Button btn_scvupdate,btn_scvdelete;

        public HolderComplain(@NonNull View itemView) {
            super(itemView);

            scvdate= itemView.findViewById(R.id.scvdate);
            scvmassage= itemView.findViewById(R.id.scvmassage);
            scvstatus=itemView.findViewById(R.id.scvstatus);

            btn_scvupdate=itemView.findViewById(R.id.btn_scvupdate);
            btn_scvdelete=itemView.findViewById(R.id.btn_scvdelete);

        }

    }

}



