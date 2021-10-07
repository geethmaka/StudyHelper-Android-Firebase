package com.example.studyhelper_android_firebase.course;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.example.studyhelper_android_firebase.classes.User;
import com.example.studyhelper_android_firebase.services.Services;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class FileHandlerAdapter extends RecyclerView.Adapter<FileHandlerAdapter.ViewHolder> {
    private List<Pdf> PdfList;
    private LayoutInflater mInflater;
    private Context mContext;
    private FragmentActivity c;

    public FileHandlerAdapter(FragmentActivity c, ArrayList<Pdf> PdfList, Context mContext) {
        this.PdfList = PdfList;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.c = c;
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
        Services services = new Services();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<User> userList = null;
        //loading data
//
        String animal = PdfList.get(position).getObj().getTitle();
        String tid = PdfList.get(position).getObj().getTid();
        String id = PdfList.get(position).getId();

        holder.myTextView.setText(PdfList.get(position).getObj().getTitle());

        holder.delete.setOnClickListener(view -> {
// Create a storage reference from our app
            StorageReference storageRef = storage.getReference();

// Create a reference to the file to delete
            StorageReference desertRef = storageRef.child(animal);


// Delete the file
            AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create(); //Read Update
            alertDialog.setTitle("Confirm Delete");
            alertDialog.setMessage("Are you sure want to Delete?");

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog, ID) -> {
                desertRef.delete().addOnSuccessListener(aVoid -> {
                    db.collection("pdf").document(id).delete().addOnCompleteListener(del -> {
                        db.collection("users").get().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    User u = new User(document.getId(), document.toObject(User.class));
                                    if (u.getId().equals(tid)) {
                                        services.sendMail(u.getUser().getEmail(), "Study-Helper File Deletion Notice", "We are sorry to inform that your file has been deleted.");
                                        break;
                                    }
                                }

                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        });
                    });
                }).addOnFailureListener(exception -> {
                    Log.d("err", String.valueOf(exception));
                });
            });

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialog, id1) -> dialog.dismiss());
            alertDialog.show();
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
