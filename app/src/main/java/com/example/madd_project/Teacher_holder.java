package com.example.madd_project;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class Teacher_holder extends RecyclerView.ViewHolder {

    ImageButton edit,update,delete;
    TextView subject_heading,subject,title;
    EditText edit_title,update_pdf;

    public Teacher_holder(@NonNull View itemView) {
        super(itemView);
        this.edit = itemView.findViewById(R.id.edit);
        this.update = itemView.findViewById(R.id.update);
        this.delete = itemView.findViewById(R.id.delete);
        this.subject_heading = itemView.findViewById(R.id.pdf);
        this.subject = itemView.findViewById(R.id.pdf1);
        this.title = itemView.findViewById(R.id.pdf2);
        this.edit_title = itemView.findViewById(R.id.editpdf);
        this.update_pdf = itemView.findViewById(R.id.updatepdf);


    }



}
