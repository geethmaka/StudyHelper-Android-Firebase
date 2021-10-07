package com.example.studyhelper_android_firebase.complain;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;
import com.example.studyhelper_android_firebase.services.Services;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Adapter_activeUser extends RecyclerView.Adapter<Adapter_activeUser.ViewHolder> implements Filterable {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ArrayList<User> userArrayList;//newsArrayListfull
    ArrayList<User> searchArrayList;//newsArraylist

    public Adapter_activeUser(Context context, ArrayList<User> newArrayList) {
        this.context = context;
        this.userArrayList = newArrayList;
        this.searchArrayList = new ArrayList<>(userArrayList);
    }

    @NonNull
    @Override
    public Adapter_activeUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_active_user,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //create user object and get the array list
        User user = userArrayList.get(position);

        holder.username.setText(user.getUser().getUsername());
        holder.type.setText(String.valueOf(user.getUser().getType()));
        holder.email.setText(user.getUser().getEmail());

        //setting action to the button
        holder.btn_banUser.setOnClickListener(v -> {
            DocumentReference userRef = db.collection("users").document(user.getId());
            //confirmation dialog box
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
            alertDialog.setTitle("Ban User");
            alertDialog.setMessage("Are you sure you want to Ban user " + user.getUsername());
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", (dialog, ID) -> userRef
                    .update("status", "inactive")
                    .addOnSuccessListener(aVoid -> {
                        userRef
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
                                        String subject = "You acount have been banned from Study helper";
                                        String content = "Hello " + username + ",\nYour Study helper account has been banned due to violation of our guidelines.Email us to 'acc.manager@studyhelper.com' if you have any inquiries.\n\nThank you.";
                                        services.sendMail(email,subject,content);
                                    } else {
                                        Log.d("TAG", "No such document");
                                    }
                                } else {
                                    Log.d("TAG", "get failed with ", task1.getException());
                                }
                            });
                        //display toast for success
                        Toast.makeText(context.getApplicationContext(), "User Deactivated Successfully!!!",Toast.LENGTH_LONG).show();
                        //redirect to ActiveUsers
                        Intent i=new Intent(v.getContext(), ActiveUsers.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(i);
                        ((Activity)context).finish();
                    })

                    .addOnFailureListener(e -> {
                        //display toast for unsuccessful
                        Toast.makeText(context.getApplicationContext(), "User Deactivation unsuccessfully!!!",Toast.LENGTH_LONG).show();
                    }));
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"No", (dialog, id) -> dialog.dismiss());
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    //searching user
    @Override
    public Filter getFilter() {
        return userFilter;
    }

    private final Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //to take characters user has input
            ArrayList<User> filteredUserList = new ArrayList<>();

            //getting the text user typed inside the search view
            //checking the input is null if then show the whole list
            if(constraint == null || constraint.length() == 0) {
                //display the entire list
                filteredUserList.addAll(userArrayList);
            }
            else {  //if there are text inside the search view
                String filterPattern = constraint.toString().toLowerCase().trim();

                //filtering the data
                for(User user : userArrayList) {
                    if(user.getUser().getUsername().toLowerCase().contains(filterPattern))
                        filteredUserList.add(user);
                }
            }
            //taking the filtered result set
            FilterResults results = new FilterResults();
            results.values = filteredUserList;
            results.count = filteredUserList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            searchArrayList.clear();
            searchArrayList.addAll((ArrayList<User>)results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView type;
        TextView email;
        Button btn_banUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            username = itemView.findViewById(R.id.username);
            type = itemView.findViewById(R.id.user_type);
            email = itemView.findViewById(R.id.user_email);
            btn_banUser = itemView.findViewById(R.id.btn_banUser);
        }
    }
}
