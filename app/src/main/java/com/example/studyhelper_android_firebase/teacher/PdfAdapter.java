package com.example.studyhelper_android_firebase.teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.example.studyhelper_android_firebase.course.ViewCourses;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

//Pdf Adapter class
public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {

    Context context;
    ArrayList<Pdf> pdfArrayList;

    public PdfAdapter(ArrayList<Pdf> pdfArrayList, Context context) {
        this.context = context;
        this.pdfArrayList = pdfArrayList;
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.teacher_pdf_row, parent, false);


        return new PdfViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, int position) {
        //firebase and storage connection
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Pdf pdf = pdfArrayList.get(position);

        holder.subject.setText(pdf.getObj().getSubject());
        holder.Title.setText(pdf.getObj().getTitle());
        holder.pdf.setText(pdf.getObj().getPdf());

        String p = pdfArrayList.get(position).getObj().getTitle();
        String id = pdfArrayList.get(position).getId();

        //delete pdf function
        holder.deletePdf.setOnClickListener((View v) -> {
            // Create a storage reference from our app
            StorageReference storageRef = storage.getReference();

// Create a reference to the file to delete
            StorageReference desertRef = storageRef.child(p);
// Delete the file
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create(); //Read Update
            alertDialog.setTitle("Confirm Delete");
            alertDialog.setMessage("Are you sure want to Delete?");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog, ID) -> {
                desertRef.delete().addOnSuccessListener(aVoid -> {
                    db.collection("pdf").document(id)
                            .delete();
                    Toast.makeText(v.getContext(), "File Deleted successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, TeacherMainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }).addOnFailureListener(exception -> Log.d("err", String.valueOf(exception)));
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialog, id1) -> dialog.dismiss());
            alertDialog.show();




        });
    }

    @Override
    public int getItemCount() {
        return pdfArrayList.size();
    }

    public static class PdfViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        EditText Title, pdf;
        ImageButton deletePdf;

        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.pdf1);
            Title = itemView.findViewById(R.id.editpdf);
            pdf = itemView.findViewById(R.id.updatepdf);
            deletePdf = itemView.findViewById(R.id.delete);
//            TextView btn_banUser = itemView.findViewById(R.id.btn_banUser);
        }
    }

}
