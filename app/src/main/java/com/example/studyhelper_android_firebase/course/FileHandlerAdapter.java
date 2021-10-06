package com.example.studyhelper_android_firebase.course;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class FileHandlerAdapter extends RecyclerView.Adapter<FileHandlerAdapter.ViewHolder> {
    private List<Pdf> PdfList;
    private LayoutInflater mInflater;
    private Context mContext;

    public FileHandlerAdapter(ArrayList<Pdf> PdfList, Context mContext) {
        this.PdfList = PdfList;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FileHandlerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating the view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_file, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //loading data
//
        String animal = PdfList.get(position).getObj().getTitle();
        String id = PdfList.get(position).getId();

        holder.delete.setOnClickListener(view -> {
// Create a storage reference from our app
            StorageReference storageRef = storage.getReference();

// Create a reference to the file to delete
            StorageReference desertRef = storageRef.child(animal);

            Toast.makeText(view.getContext(), desertRef.getPath(), Toast.LENGTH_SHORT).show();



// Delete the file
            desertRef.delete().addOnSuccessListener(aVoid -> {
                db.collection("pdf").document(id)
                        .delete();
            }).addOnFailureListener(exception -> {
                Log.d("err", String.valueOf(exception));
            });
        });
    }


    @Override
    public int getItemCount() {
        return PdfList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView parentLayout;
        TextView myTextView;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//
            myTextView = itemView.findViewById(R.id.fileName);
            delete = itemView.findViewById(R.id.deleteButton);
            parentLayout = itemView.findViewById(R.id.file_parent_layout);
        }
    }
}
