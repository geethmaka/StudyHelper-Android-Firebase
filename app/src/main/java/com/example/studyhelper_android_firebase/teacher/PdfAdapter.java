package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Pdf;

import java.io.File;
import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {

    Context context;
    ArrayList< Pdf>pdfArrayList;

    public PdfAdapter(ArrayList<Pdf> pdfArrayList,Context context ) {
        this.context = context;
        this.pdfArrayList = pdfArrayList;
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.teacher_pdf_row,parent, false);


        return new PdfViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, int position) {

            Pdf pdf= pdfArrayList.get(position);

            holder.subject.setText(pdf.subject);
            holder.Title.setText(pdf.title);
//            holder.uppdf.setFile(pdf.uppdf);
    }
    @Override
    public int getItemCount() {
        return pdfArrayList.size();
    }

    public static class PdfViewHolder extends RecyclerView.ViewHolder{

        TextView subject,Title;
//        File uppdf;



        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
