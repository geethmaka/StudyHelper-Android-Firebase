package com.example.studyhelper_android_firebase.student;

import android.content.Context;
import android.graphics.Paint;
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
import java.util.ArrayList;

public class S_adptercomplain extends RecyclerView.Adapter<S_adptercomplain.HolderComplain>{

    private Context context;
    public ArrayList<Complain> complainList;
//    private Filtercomplain filter;

    public S_adptercomplain(Context context, ArrayList<Complain> productList) {
        this.context = context;
        this.complainList = complainList;
    }

    @NonNull
    @Override
    public HolderComplain onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //inflate layout
        View view= LayoutInflater.from(context).inflate(R.layout.sitem,parent,false);
        return new HolderComplain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull S_adptercomplain.HolderComplain holder, int position) {
        // get data
        Complain cp= complainList.get(position);

        String date= cp.getDate();
        String content= cp.getContent();
        String status= cp.getStatus();

        //set data
        holder.scvdate.setText(date);
        holder.scvmassage.setText(content);
        holder.scvstatus.setText(status);

    }

//
//
//        //show dialog
//        bottomSheetDialog.show();
//
//        //edit click
//        editbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog.dismiss();
//                //open edit product actitvity
//                Intent intent = new Intent(context, EditProduct.class);
//                intent.putExtra("productId", id);
//                context.startActivity(intent);
//            }
//        });
//
//        //delete click
//        deletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog.dismiss();
//                //show delete confirm dialog
//                AlertDialog.Builder builder= new AlertDialog.Builder(context);
//                builder.setTitle("Delete")
//                        .setMessage("Are you sure you want to delete item? "+title+" ?")
//                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //delete
//                                deleteProduct(id);//id is the product id
//                            }
//                        })
//                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //cancel deletion
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
//            }
//        });
//
//    }
 //delete part
//    private void deleteProduct(String id) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
//        ref.child(firebaseAuth.getUid()).child("Products").child(id).removeValue()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(context, "Product Deleted...", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        //failed
//                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    @Override
    public int getItemCount() {
        return complainList.size();
    }
//
//    @Override
//    public Filter getFilter() {
//        if(filter==null){
//            filter= new FilterProduct(this,filterList);
//        }
//        return filter;
//    }

    class HolderComplain extends RecyclerView.ViewHolder{
        //holds views of recyclerview

        private TextView scvdate,scvstatus;
        private EditText scvmassage;

        public HolderComplain(@NonNull View itemView) {
            super(itemView);

            scvdate= itemView.findViewById(R.id.scvdate);
            scvmassage= itemView.findViewById(R.id.scvmassage);
            scvstatus=itemView.findViewById(R.id.scvstatus);

        }

    }

}




