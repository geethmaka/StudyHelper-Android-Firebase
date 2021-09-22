package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Link;
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

//                washingtonRef
//                        .update("subject", subject.getText().toString(), "grade", 10, "availability", availability.isChecked())
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Log.d("TAG", "DocumentSnapshot successfully updated!");
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w("TAG", "Error updating document", e);
//                            }
//                        });
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
//        EditText ampm;


        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.link1);
            title = itemView.findViewById(R.id.editlinktitle);
            date = itemView.findViewById(R.id.calendarView);
            time= itemView.findViewById(R.id.amPm);
//            ampm=itemView.findViewById(R.id.spinner2);
            link = itemView.findViewById(R.id.updatelink);

            updateLink = itemView.findViewById(R.id.update_link);
        }
    }
}
