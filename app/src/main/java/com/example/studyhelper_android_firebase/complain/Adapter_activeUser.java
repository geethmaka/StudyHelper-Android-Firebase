package com.example.studyhelper_android_firebase.complain;

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
import com.example.studyhelper_android_firebase.classes.User;
import com.example.studyhelper_android_firebase.course.ViewCourses;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Adapter_activeUser extends RecyclerView.Adapter<Adapter_activeUser.ViewHolder> {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    ArrayList<User> userArrayList;
    ArrayList<User> searchArrayList;

    public Adapter_activeUser(Context context, ArrayList<User> newArrayList) {
        this.context = context;
        this.userArrayList = newArrayList;
//        this.searchArrayList = new ArrayList<>(searchArrayList);
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

        holder.btn_banUser.setOnClickListener(v -> {
            DocumentReference userRef = db.collection("users").document(user.getId());
            userRef.update("status", "inactive")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context.getApplicationContext(), "User Deactivated Successfully!!!",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(v.getContext(), ActiveUsers.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            v.getContext().startActivity(i);
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context.getApplicationContext(), "User Deactivation unsuccessfully!!!",Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    //searching user
//    @Override
//    public Filter getFilter() {
//        return userFilter;
//    }
//
//    private final Filter userFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            //to take characters user has input
//            ArrayList<User> filteredUserList = new ArrayList<>();
//
//            //if the search is null or lenght of the array is 0
//            if(constraint == null || constraint.length() == 0) {
//                //display the entire list
//                filteredUserList.addAll(searchArrayList);
//            }
//            else {  //if there are text inside the search view
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                //filtering the data
//                for(User user : searchArrayList) {
//                    if(user.getUsername().toLowerCase().contains(filterPattern))
//                        filteredUserList.add(user);
//                }
//            }
//            //taking the filtered result set
//            FilterResults results = new FilterResults();
//            results.values = filteredUserList;
//            results.count = filteredUserList.size();
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            userArrayList.clear();
//            userArrayList.addAll((ArrayList)results.values);
//            notifyDataSetChanged();
//        }
//    };

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
