package com.example.studyhelper_android_firebase.student;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {
    private List<Pdf> pdfList;
    private LayoutInflater mInflater;
    private Context mContext;

    public PdfAdapter(ArrayList<Pdf> courses, Context mContext) {
        this.pdfList = courses;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating the view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_pdf_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //loading data
        FirebaseStorage storage = FirebaseStorage.getInstance();

//        String subject = courseList.get(position).getCourse().getSubject();
//        String Cid = courseList.get(position).getId();

        Pdf pdf = pdfList.get(position);

        holder.myTextView.setText(pdf.getObj().getTitle());

        holder.download.setOnClickListener(view -> {
            StorageReference httpsReference = storage.getReferenceFromUrl(pdf.getObj().getPdf());
        });
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView myTextView;
        Button download;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myTextView = itemView.findViewById(R.id.pdfTitle);
            parentLayout = itemView.findViewById(R.id.studentPdfLayout);
            download = itemView.findViewById(R.id.button3);
        }
    }
}
