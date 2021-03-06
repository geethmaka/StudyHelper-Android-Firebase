package com.example.studyhelper_android_firebase;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.studyhelper_android_firebase.classes.IPdf;
import com.example.studyhelper_android_firebase.services.Services;
import com.example.studyhelper_android_firebase.teacher.Pdfs_added;
import com.example.studyhelper_android_firebase.teacher.TeacherMainActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

//creating teacher pdf class
public class Teacher_popup_Pdf extends AppCompatActivity {
    //check emtiness for testcases
    public boolean checkForEmpty(String title, String subject, String notify) {
        if ((!title.equals("") && (!subject.equals("Subject")) && (!notify.equals("")))) {
            return false;
        } else {
            return true;
        }
    }

    //declare variables
    TextView notifyPdf;
    FirebaseStorage storage;
    Uri pdfUri;
    ProgressDialog progressDialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_popup_pdf);
        //database connection
        storage = FirebaseStorage.getInstance();

        Button selectPdf = findViewById(R.id.selectPdf);
        notifyPdf = findViewById(R.id.notifyPdf);
        Button uploadButton = findViewById(R.id.btn_uplodpdf);
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        pdfUri = data.getData();
                        notifyPdf.setText("A file is selected" + data.getData().getLastPathSegment());
                    }
                });
        //select pdf function
        selectPdf.setOnClickListener(v -> {
            //check storage permission
            if (ContextCompat.checkSelfPermission(Teacher_popup_Pdf.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                someActivityResultLauncher.launch(intent);
            } else
                ActivityCompat.requestPermissions(Teacher_popup_Pdf.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
        });
        //check pdf added or not
        uploadButton.setOnClickListener((View v) -> {
            if (pdfUri != null) {
                uploadPdf(pdfUri);
            } else
                Toast.makeText(Teacher_popup_Pdf.this, "Select a pdf", Toast.LENGTH_SHORT).show();
        });
    }

    //upload pdf function
    private void uploadPdf(Uri pdfUri) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Uploading File");
        progressDialog.setProgress(0);
        progressDialog.show();
        String fileName = System.currentTimeMillis() + "";
        StorageReference storageReference = storage.getReference();
        EditText filename = findViewById(R.id.editTextPdf);
        StorageReference ref = storageReference.child(filename.getText().toString());
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
        UploadTask uploadTask = ref.putFile(pdfUri);
        Task<Uri> urlTask = uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            return ref.getDownloadUrl();
            //insert data into database
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                Toast.makeText(Teacher_popup_Pdf.this, "File successfully uploaded!", Toast.LENGTH_LONG).show();
                Spinner Subject = findViewById(R.id.spinnerpdf);
                TextView Title = findViewById(R.id.editTextPdf);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String id = preferences.getString("uid", "");

                IPdf pdf = new IPdf(id, Subject.getSelectedItem().toString(), Title.getText().toString(), downloadUri.toString());
                db.collection("pdf")
                        .add(pdf)
                        .addOnSuccessListener(documentReference -> {

                            Services services = new Services();
                            //sending a mail to the course manager
                            String email = "geethmaka@gmail.com";
                            String subject = "Teacher added pdf";
                            String title = Subject.getSelectedItem().toString();
                            String content = "Teacher id:" + id + " added a pdf to " + title + "";
                            services.sendMail(email, subject, content);

                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            progressDialog.setProgress(100);
                            progressDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(), TeacherMainActivity.class);
                            startActivity(intent);
                        })


                        .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e));


            } else {
            }

        });
    }

    //check storage permisson
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();
        } else
            Toast.makeText(Teacher_popup_Pdf.this, "please proivde permission..", Toast.LENGTH_SHORT).show();
    }

    //check pdf select or not
    private void selectPdf() {
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        pdfUri = data.getData();
                        notifyPdf.setText("A file is selected" + data.getData().getLastPathSegment());
                    }
                });
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        someActivityResultLauncher.launch(intent);

    }

}