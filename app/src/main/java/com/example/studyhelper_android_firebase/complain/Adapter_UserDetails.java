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
import com.example.studyhelper_android_firebase.classes.User;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class Adapter_UserDetails extends RecyclerView.Adapter<Adapter_UserDetails.ViewHolder> {

    Context context;
    ArrayList<User> newArrayList;

    public Adapter_UserDetails(Context context, ArrayList<User> newArrayList) {
        this.context = context;
        this.newArrayList = newArrayList;
    }

    @NonNull
    @Override
    public Adapter_UserDetails.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_userdetails,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_UserDetails.ViewHolder holder, int position) {
        User user = newArrayList.get(position);
        holder.username.setText(user.username);
        holder.type.setText(user.type);
        holder.email.setText(user.email);
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
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
            itemView.findViewById(R.id.btn_banUser).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
