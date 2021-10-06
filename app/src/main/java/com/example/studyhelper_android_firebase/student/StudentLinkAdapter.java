package com.example.studyhelper_android_firebase.student;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Link;
import com.example.studyhelper_android_firebase.classes.Pdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class StudentLinkAdapter extends RecyclerView.Adapter<StudentLinkAdapter.ViewHolder> {
    private List<Link> linkList;
    private LayoutInflater mInflater;
    private Context mContext;

    public StudentLinkAdapter(ArrayList<Link> courses, Context mContext) {
        this.linkList= courses;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating the view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_link_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentLinkAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //loading data

        String subject = linkList.get(position).getObj().getTitle();
//       String Cid = linkList.get(position).getId();

       holder.linktitle.setText(subject);

       holder.parentLayout.setOnClickListener(view -> {

        });


    }

    @Override
    public int getItemCount() {
        return linkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView parentLayout;
        TextView linktitle;
        TextView linksubject;
        TextView linklink;
        TextView linkdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linktitle = itemView.findViewById(R.id.linkTitle);
            linksubject = itemView.findViewById(R.id.linksubject);
            linklink = itemView.findViewById(R.id.linklink);
            linkdate = itemView.findViewById(R.id.linkdate);
            parentLayout = itemView.findViewById(R.id.studentLinkLayout);
        }
    }
}
