package com.example.studyhelper_android_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studyhelper_android_firebase.classes.User;;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
;




public class Register extends AppCompatActivity {

    //UI Views
    private EditText reg_name,reg_mn,reg_email,reg_pw;
    private Spinner reg_Stream;
    private Button reg_btn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference db;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//this line hide action bar
        setContentView(R.layout.layout_register);

        reg_name = findViewById(R.id.reg_name);
        reg_mn = findViewById(R.id.reg_mn);
        reg_Stream = findViewById(R.id.reg_Stream);
        reg_email = findViewById(R.id.reg_email);
        reg_pw = findViewById(R.id.reg_pw);

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        reg_btn = findViewById(R.id.reg_btn);


        //Initialize permission arry
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);



        reg_btn.setOnClickListener(v -> {
            db = database.getReference().child("User");
            //input data

            //validate data
            if (TextUtils.isEmpty(reg_name.getText().toString())) {
                Toast.makeText(this, "Enter Name...", Toast.LENGTH_SHORT).show();
                return;
            }

            else if (TextUtils.isEmpty(radioButton.getText().toString())) {
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
                user = new User(
                        reg_name.getText().toString().trim(),
                        radioButton.getText().toString(),
                        reg_Stream.getSelectedItem().toString(),
                        "active",
                        reg_pw.getText().toString().trim(),
                        Long.parseLong(reg_mn.getText().toString().trim()),
                        reg_email.getText().toString().trim()

                );
                //insert into the database...
                database.getReference().child("User/" + user.getId()).setValue(user);
                //feedback to the user
                Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_LONG).show();


            }

        });

  }

}