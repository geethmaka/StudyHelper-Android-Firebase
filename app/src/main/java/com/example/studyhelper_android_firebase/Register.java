package com.example.studyhelper_android_firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studyhelper_android_firebase.classes.iUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class Register extends AppCompatActivity {

    //UI Views
    EditText reg_name,reg_mn,reg_email,reg_pw;
    Spinner reg_Stream, reg_type;
    Button reg_btn;

    FirebaseFirestore db = FirebaseFirestore.getInstance();;

    iUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//this line hide action bar
        setContentView(R.layout.layout_register);

        reg_name = findViewById(R.id.reg_name);
        reg_mn = findViewById(R.id.reg_mn);
        reg_type = findViewById(R.id.reg_type);
        reg_Stream = findViewById(R.id.reg_Stream);
        reg_email = findViewById(R.id.reg_email);
        reg_pw = findViewById(R.id.reg_pw);


        reg_btn = findViewById(R.id.reg_btn);

        //Initialize permission arry
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        reg_btn.setOnClickListener(v -> {
            //validate data
            if (TextUtils.isEmpty(reg_name.getText().toString())) {
                Toast.makeText(this, "Enter Name...", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (TextUtils.isEmpty(reg_type.getSelectedItem().toString())) {
                Toast.makeText(this, "Enter Steam Name...", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (TextUtils.isEmpty(reg_Stream.getSelectedItem().toString())) {
                Toast.makeText(this, "Enter Steam Name...", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (TextUtils.isEmpty(reg_mn.getText().toString())) {
                Toast.makeText(this, "Enter Phone Number...", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(reg_email.getText().toString()).matches()) {
                Toast.makeText(this, "Invalid Email Address...", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (TextUtils.isEmpty(reg_email.getText().toString())) {
                Toast.makeText(this, "Enter the email Address...", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (reg_pw.getText().toString().length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters long...", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (TextUtils.isEmpty(reg_pw.getText().toString())) {
                Toast.makeText(this, "Enter Password...", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                //take inputs from the user and then to this instance (user) of the User.
                user = new iUser(
                        reg_name.getText().toString().trim(),
                        reg_type.getSelectedItem().toString(),
                        reg_Stream.getSelectedItem().toString().trim(),
                        "active",
                        reg_pw.getText().toString().trim(),
                        Long.parseLong(reg_mn.getText().toString().trim()),
                        reg_email.getText().toString().trim()

                );
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(getApplicationContext(),"Successfully Registered!!!",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(this, Login.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            this.startActivity(i);
                        })
                        .addOnFailureListener(e -> Log.w("TAG", "Error adding document", e));
            }

        });

    }

    public void gotoLogin(View view) {
        final Context context = this;
        Intent intent = new Intent(context, Login.class);
        startActivity(intent);
    }
}