package com.example.studyhelper_android_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyhelper_android_firebase.classes.Link;
import com.example.studyhelper_android_firebase.classes.Pdf;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Teacher_popup_Pdf extends AppCompatActivity {

    TextView notifyPdf;
    FirebaseStorage storage;
    Uri pdfUri;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_popup_pdf);

        storage=FirebaseStorage.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button selectPdf=findViewById(R.id.selectPdf);
        notifyPdf=findViewById(R.id.notifyPdf);
        Button uploadButton = findViewById(R.id.btn_uplodpdf);

        selectPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(Teacher_popup_Pdf.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

                    selectPdf();

                }
                else
                    ActivityCompat.requestPermissions(Teacher_popup_Pdf.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);

            }
        });






        uploadButton.setOnClickListener((View v) -> {


            if (pdfUri!=null){

                uploadPdf(pdfUri);

            }
            else
                Toast.makeText(Teacher_popup_Pdf.this,"Select a pdf",Toast.LENGTH_SHORT).show();


            Spinner Subject =findViewById(R.id.spinnerpdf);
            TextView Title =findViewById(R.id.editTextPdf);
            TextView Pdfupload =findViewById(R.id.editTextselect);



            Pdf pdf=new Pdf(Subject.getSelectedItem().toString(),Title.getText().toString(),Pdfupload.getText().toString());



            db.collection("pdf")
                    .add(pdf)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                        }
                    });




        });

    }

    private void uploadPdf(Uri pdfUri) {

        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File");
        progressDialog.setProgress(0);
        progressDialog.show();
        String fileName=System.currentTimeMillis()+"";
        StorageReference storageReference= storage.getReference();
        storageReference.child("Uploads").child(fileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String url=taskSnapshot.getDownloadUrl().toSting();

                DatabaseReference reference=db.getReference();

                reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Teacher_popup_Pdf.this,"File successfully uploaded",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(Teacher_popup_Pdf.this,"File not successfully uploaded",Toast.LENGTH_LONG).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Teacher_popup_Pdf.this,"File not successfully uploaded",Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                int currentProgress=100*taskSnapshot.getBytesTransferred()/taskSnapShot.getTotalByteCount();
                progressDialog.setProgress(currentProgress);


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();;
        }
        else
            Toast.makeText(Teacher_popup_Pdf.this,"please proivde permission..",Toast.LENGTH_SHORT).show();
    }

    private void selectPdf() {

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==86 && resultCode==RESULT_OK && data!=null){

            pdfUri=data.getData();
            notifyPdf.setText("A file is selected"+data.getData().getLastPathSegment());

        }
        else{
            Toast.makeText(Teacher_popup_Pdf.this,"Please select a file",Toast.LENGTH_SHORT).show();
        }

    }
}