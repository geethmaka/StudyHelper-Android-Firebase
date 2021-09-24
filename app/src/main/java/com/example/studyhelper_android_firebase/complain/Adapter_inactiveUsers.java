package com.example.studyhelper_android_firebase.complain;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Adapter_inactiveUsers extends RecyclerView.Adapter<Adapter_inactiveUsers.ViewHolder> {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ArrayList<User> userArrayList;
    ArrayList<User> searchArrayList;

    public Adapter_inactiveUsers(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public Adapter_inactiveUsers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_inactive_user,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_inactiveUsers.ViewHolder holder, int position) {
        //create user object and get the array list
        User user = userArrayList.get(position);

        holder.username.setText(user.getUser().getUsername());
        Log.w("username", user.getUser().getUsername());
        holder.type.setText(String.valueOf(user.getUser().getType()));
        holder.email.setText(user.getUser().getEmail());

        holder.btn_deleteUser.setOnClickListener((View v) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
            alertDialog.setTitle("Delete User confirmation");
            alertDialog.setMessage("Do you want to Delete the User");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", (dialog, ID) ->
                    db.collection("users").document(user.getId()).delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context.getApplicationContext(), "The user is Deleted Successfully!!!",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(v.getContext(), InactiveUsers.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            v.getContext().startActivity(i);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    }));
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"No", (dialog, id1) -> dialog.dismiss());
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView type;
        TextView email;
        Button btn_deleteUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            username = itemView.findViewById(R.id.username);
            type = itemView.findViewById(R.id.user_type);
            email = itemView.findViewById(R.id.user_email);
            btn_deleteUser = itemView.findViewById(R.id.btn_Delete_User);
        }
    }
}
