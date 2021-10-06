package com.example.studyhelper_android_firebase.course;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Course> courseList;
    private LayoutInflater mInflater;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Course> courses, Context mContext) {
        this.courseList= courses;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating the view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //loading data

        String subject = courseList.get(position).getCourse().getSubject();
        String Cid = courseList.get(position).getId();

        holder.myTextView.setText(subject);

        holder.parentLayout.setOnClickListener(view -> {
            Intent i=new Intent(mContext,UpdateCourse.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("id", Cid);
            mContext.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView myTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myTextView = itemView.findViewById(R.id.courseName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
