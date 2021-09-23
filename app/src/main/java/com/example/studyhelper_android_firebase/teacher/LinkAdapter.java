package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Link;
import com.example.studyhelper_android_firebase.course.ViewCourses;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            holder.subject.setText(link.getObj().getSubject());
            holder.title.setText(link.getObj().getTitle());
            holder.date.setDate(link.getObj().getDate());
            holder.time.setText(link.getObj().getTime());
            holder.link.setText(link.getObj().getLink());

            holder.updateLink.setOnClickListener(v ->{
                DocumentReference washingtonRef = db.collection("link").document(link.getId());

                washingtonRef
                        .update("subject",  holder.subject.getText().toString(), "title",holder.title.getText().toString(), "date", holder.date.getDate(),"time",holder.time.getText().toString())
                        .addOnSuccessListener(aVoid ->{ Log.d("TAG", "DocumentSnapshot successfully updated!"+link.getId());
                            holder.subject.setText(link.getObj().getSubject());
                            holder.title.setText(holder.title.getText().toString());
                            holder.date.setDate(holder.date.getDate());
                            holder.time.setText(holder.time.getText().toString());
                            holder.link.setText(holder.link.getText().toString());})
                        .addOnFailureListener(e -> Log.w("TAG", "Error updating document", e));
            });

            holder.deleteLink.setOnClickListener((View v) -> {
                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Are you sure you want to delete this link");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", (dialog, ID) -> db.collection("link").document(link.getId())
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            Intent i=new Intent(v.getContext(), Links_added.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            v.getContext().startActivity(i);
                        })
                        .addOnFailureListener(e -> {
                        }));
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"No", (dialog, id) -> dialog.dismiss());
                alertDialog.show();
            });


        }

        @Override
        public int getItemCount () {
            return linkArrayList.size();
        }

    public static class LinkViewHolder extends RecyclerView.ViewHolder{
        TextView subject;
        EditText title,link,time;
        CalendarView date;
        ImageButton updateLink;
        ImageButton deleteLink;
//        EditText ampm;


        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.link1);
            title = itemView.findViewById(R.id.editlinktitle);
            date = itemView.findViewById(R.id.calendarView);
            time= itemView.findViewById(R.id.amPm);
//            ampm=itemView.findViewById(R.id.spinner2);
            link = itemView.findViewById(R.id.updatelink);
            deleteLink=itemView.findViewById(R.id.delete_link);
            updateLink = itemView.findViewById(R.id.update_link);
        }
    }
}
