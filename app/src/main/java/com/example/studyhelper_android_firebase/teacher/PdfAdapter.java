package com.example.studyhelper_android_firebase.teacher;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {

    Context context;
    ArrayList<Pdf>pdfArrayList;

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

        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Pdf pdf = pdfArrayList.get(position);

        holder.subject.setText(pdf.subject);
        holder.Title.setText(pdf.title);
        holder.pdf.setText(pdf.pdf);

        String animal = pdfArrayList.get(position).getObj().getTitle();
        String id = pdfArrayList.get(position).getId();

        holder.deletePdf.setOnClickListener((View v) -> {
            // Create a storage reference from our app
            StorageReference storageRef = storage.getReference();

// Create a reference to the file to delete
            StorageReference desertRef = storageRef.child(animal);

            Toast.makeText(v.getContext(), desertRef.getPath(), Toast.LENGTH_SHORT).show();



// Delete the file
            desertRef.delete().addOnSuccessListener(aVoid -> db.collection("pdf").document(id)
                    .delete()).addOnFailureListener(exception -> Log.d("err", String.valueOf(exception)));
            ((Activity)context).finish();
        });
    }
    @Override
    public int getItemCount() {
        return pdfArrayList.size();
    }

    public static class PdfViewHolder extends RecyclerView.ViewHolder{
        TextView subject;
        EditText Title,pdf;
        ImageButton deletePdf;

        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.pdf1);
            Title = itemView.findViewById(R.id.editpdf);
            pdf = itemView.findViewById(R.id.updatepdf);
            deletePdf=itemView.findViewById(R.id.delete);
//            TextView btn_banUser = itemView.findViewById(R.id.btn_banUser);
        }
    }

}
