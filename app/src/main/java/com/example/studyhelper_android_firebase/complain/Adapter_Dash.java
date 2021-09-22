package com.example.studyhelper_android_firebase.complain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Complain;

import java.util.ArrayList;

public class Adapter_Dash extends RecyclerView.Adapter<Adapter_Dash.ViewHolder> {

    Context context;
    ArrayList<Complain> complainArrayList;

    public Adapter_Dash(Context context, ArrayList<Complain> complainArrayList) {
        this.context = context;
        this.complainArrayList = complainArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complain_cv_dashboard,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Complain c = complainArrayList.get(position);
        holder.username.setText(c.getUserID());
        holder.status.setText(c.getStatus());
        holder.complain.setText(c.getContent());
    }

    @Override
    public int getItemCount() {
        return complainArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView status;
        TextView complain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_complain_name);
            status = itemView.findViewById(R.id.tv_status);
            complain = itemView.findViewById(R.id.user_complain);
        }
    }
}
