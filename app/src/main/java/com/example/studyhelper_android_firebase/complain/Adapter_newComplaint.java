package com.example.studyhelper_android_firebase.complain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;
import com.example.studyhelper_android_firebase.classes.User;
import com.example.studyhelper_android_firebase.services.Services;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Adapter_newComplaint extends RecyclerView.Adapter<Adapter_newComplaint.ViewHolder> {
    //Test case
    public boolean checkForEmpty(String subject, String stream) {
        if ((!subject.equals("") && (!stream.equals("Subject")))) {
            return false;
        } else {
            return true;
        }
    }

    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ArrayList<Complain> complainArrayList;

    public Adapter_newComplaint(Context context, ArrayList<Complain> newArrayList) {
        this.context = context;
        this.complainArrayList = newArrayList;
    }

    @NonNull
    @Override
    public Adapter_newComplaint.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_pending, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_newComplaint.ViewHolder holder, int position) {
        Complain complain = complainArrayList.get(position);

        //getting the date
        holder.date.setText(complain.getComplain().getDate());
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
        //getting the status
        holder.status.setText(complain.getComplain().getStatus());
        //getting the complain content
        holder.complain.setText(complain.getComplain().getContent());

        //adding onclick function to the ban user button
        holder.btn_cResolve.setOnClickListener(v -> {
            DocumentReference complainRef = db.collection("complain").document(complain.getComplainId());
            complainRef.update("status", "Resolved")
                    .addOnSuccessListener(aVoid -> {
                        complainRef.get()
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
                                                                Services services = new Services();
                                                                //sending a mail to the user
                                                                String email = u.getEmail();
                                                                String username = u.getUsername();
                                                                String subject = "Notify Complaint Resolved";
                                                                String content = "Hello " + username + ",\nThank you for your patience we have resolved your complaint.\n\nThank you.";
                                                                services.sendMail(email, subject, content);
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
                        //toast successful message
                        Toast.makeText(context.getApplicationContext(), "Complaint Marked Resolved Successful!!!", Toast.LENGTH_LONG).show();
                        //redirect to NewComplaint class
                        Intent i = new Intent(this.context.getApplicationContext(), NewComplaint.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.context.startActivity(i);
                        ((Activity) context).finish();
                    })

                    .addOnFailureListener(e -> {
                        //toast unsuccessful message
                        Log.w("TAG", "Error updating status", e);
                        Toast.makeText(context.getApplicationContext(), "Error!!!", Toast.LENGTH_LONG).show();
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
        TextView date;
        Button btn_cResolve;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_complain_name);
            status = itemView.findViewById(R.id.tv_status);
            complain = itemView.findViewById(R.id.user_complain);
            date = itemView.findViewById(R.id.tv_date);
            btn_cResolve = itemView.findViewById(R.id.btn_complain_resolve);
        }
    }
}
