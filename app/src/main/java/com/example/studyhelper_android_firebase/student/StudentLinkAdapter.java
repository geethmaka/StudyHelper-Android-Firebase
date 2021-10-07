package com.example.studyhelper_android_firebase.student;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Link;
import com.example.studyhelper_android_firebase.classes.Pdf;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StudentLinkAdapter extends RecyclerView.Adapter<StudentLinkAdapter.ViewHolder> {
    private List<Link> linkList;
    private LayoutInflater mInflater;
    private Context mContext;

    public StudentLinkAdapter(ArrayList<Link> courses, Context mContext) {
        this.linkList = courses;
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
        holder.linksubject.setText(linkList.get(position).getObj().getSubject());
        holder.linklink.setText(linkList.get(position).getObj().getLink());
        holder.linkTime.setText(linkList.get(position).getObj().getTime());
        Date date = new Date(linkList.get(position).getObj().getDate());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);
        holder.linkdate.setText(strDate);
        holder.parentLayout.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", linkList.get(position).getObj().getLink());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(mContext, "Link Copied", Toast.LENGTH_SHORT).show();
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
        TextView linkTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linktitle = itemView.findViewById(R.id.linkTitle);
            linksubject = itemView.findViewById(R.id.linksubject);
            linklink = itemView.findViewById(R.id.linklink);
            linkdate = itemView.findViewById(R.id.linkdate);
            parentLayout = itemView.findViewById(R.id.studentLinkLayout);
            linkTime= itemView.findViewById(R.id.timelable);
        }
    }
}
