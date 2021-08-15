package com.example.madd_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//public class Teacher_adapter extends RecyclerView.Adapter<Teacher_holder> {
//
//    Context c;
//    ArrayList<Teacher_pdf_model>models;  //create a list of array which define in Teacher_pdf_model class
//cd
//    public Teacher_adapter(Context c, ArrayList<Teacher_pdf_model> models) {
//        this.c = c;
//        this.models = models;
//    }
//
//    @NonNull
//    @Override
//    public Teacher_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
////        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teacher_pdf_row, root:null);
////        return new Teacher_holder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Teacher_holder teacher_holder, int i) {
//
//        teacher_holder.subject_heading.setText(models.get(i).getSub_name());
//        teacher_holder.subject.setText(models.get(i).getSubject());
//        teacher_holder.title.setText(models.get(i).getTitle_sub());
//        teacher_holder.edit_title.setText(models.get(i).getEdit_title());
//        teacher_holder.update_pdf.setText(models.get(i).getPdfUpload());
////        teacher_holder.subject_heading.setImeActionLabel(models.get(i).getDelete());
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return models.size();
//    }

