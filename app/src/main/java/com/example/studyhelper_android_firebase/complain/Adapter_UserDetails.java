package com.example.studyhelper_android_firebase.complain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;

import java.util.ArrayList;

public class Adapter_UserDetails extends RecyclerView.Adapter<Adapter_UserDetails.ViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public Adapter_UserDetails(Context context, ArrayList<User> newArrayList) {
        this.context = context;
        this.userArrayList = newArrayList;
    }

    @NonNull
    @Override
    public Adapter_UserDetails.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_userdetails,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_UserDetails.ViewHolder holder, int position) {
        //create user object and get the array list
        User user = userArrayList.get(position);

        holder.username.setText(user.getUsername());
        holder.type.setText(String.valueOf(user.getType()));
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView type;
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            type = itemView.findViewById(R.id.user_type);
            email = itemView.findViewById(R.id.user_email);
            TextView btn_banUser = itemView.findViewById(R.id.btn_banUser);
//            itemView.findViewById(R.id.btn_banUser).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }
}
