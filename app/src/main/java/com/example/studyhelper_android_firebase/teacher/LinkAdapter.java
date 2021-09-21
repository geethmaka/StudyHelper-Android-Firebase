package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Link;

import java.util.ArrayList;


public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {

    Context context;
    ArrayList<Link> linkArrayList;


    public LinkAdapter(ArrayList<Link> linkArrayList, Context context) {
        this.context = context;
        this.linkArrayList = linkArrayList;
    }

        @NonNull
        @Override
        public LinkViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){

            View v = LayoutInflater.from(context).inflate(R.layout.teacher_link_row, parent, false);
            return new LinkViewHolder(v);
        }

        @Override
        public void onBindViewHolder (@NonNull LinkAdapter.LinkViewHolder holder,int position){

            Link link = linkArrayList.get(position);

            holder.subject.setText(link.getSubject());
            holder.title.setText(link.getTitle());
            holder.date.setDate(link.getDate());
            holder.time.setText(link.getTime());
//            holder.ampm.setText(link.getAmPm());
            holder.link.setText(link.getLink());

        }

        @Override
        public int getItemCount () {
            return linkArrayList.size();
        }

    public static class LinkViewHolder extends RecyclerView.ViewHolder{
        TextView subject;
        EditText title,link,time;
        CalendarView date;
//        EditText ampm;


        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.link1);
            title = itemView.findViewById(R.id.editlinktitle);
            date = itemView.findViewById(R.id.calendarView);
            time= itemView.findViewById(R.id.amPm);
//            ampm=itemView.findViewById(R.id.spinner2);
            link = itemView.findViewById(R.id.updatelink);
        }
    }
}
