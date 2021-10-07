package com.example.studyhelper_android_firebase.student;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.Course;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
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
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //loading data

        String subject = pdfList.get(position).getObj().getTitle();
//        String Cid = courseList.get(position).getId();

        holder.myTextView.setText(subject);

        holder.download.setOnClickListener(view -> {
            DownloadManager manager;
            manager = (DownloadManager) mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(pdfList.get(position).getObj().getPdf());
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            request.allowScanningByMediaScanner();
            request.setTitle(subject)
                    .setDescription("File is downloading...")
                    .setDestinationInExternalFilesDir(mContext,
                            Environment.DIRECTORY_DOWNLOADS, subject + ".pdf")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            //Enqueue the download.The download will start automatically once the download manager is ready
            // to execute it and connectivity is available.
            long reference = manager.enqueue(request);
        });
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView parentLayout;
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
