////package com.example.studyhelper_android_firebase;
////
////import android.Manifest;
////import android.app.ProgressDialog;
////import android.content.Intent;
////import android.net.Uri;
////import android.os.Bundle;
////import android.text.TextUtils;
////import android.util.Patterns;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.ImageButton;
////import android.widget.ImageView;
////import android.widget.Toast;
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import com.google.android.gms.tasks.OnFailureListener;
////import com.google.android.gms.tasks.OnSuccessListener;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import java.util.HashMap;
//
//package com.example.studyhelper_android_firebase;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.util.Calendar;
//
//
//public class Register extends AppCompatActivity {
//
//    //UI Views
//    private EditText namee, shpnamee, phonee, citye, emaile, pswe;
//    private Button regbtn;
//
//    //permission constants
//    private static final int CAMERA_REQUEST_CODE = 200;
//    private static final int STORAGE_REQUEST_CODE = 300;
//    private Calendar FirebaseAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();//this line hide action bar
//        setContentView(R.layout.layout_register);
//
//        namee = findViewById(R.id.namee);
//        shpnamee = findViewById(R.id.shpnamee);
//        phonee = findViewById(R.id.phonee);
//        citye = findViewById(R.id.citye);
//        emaile = findViewById(R.id.emaile);
//        pswe = findViewById(R.id.pswe);
//        regbtn = findViewById(R.id.regbtn);
//        human = findViewById(R.id.human);
//        iconup = findViewById(R.id.iconup);
//
//        //Initialize permission arry
//
//        Object firebaseAuth = FirebaseAuth.getInstance();
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Please wait");
//        progressDialog.setCanceledOnTouchOutside(false);
//
//
//
//
//        regbtn.setOnClickListener(v -> {
//            //register user
//            inputData();
//        });
//
//    }
//
//
//    private String name, shopname, phone, city, email, psw;
//
//    private void inputData() {
//        //input data
//        name = namee.getText().toString().trim();
//        shopname = shpnamee.getText().toString().trim();
//        phone = phonee.getText().toString().trim();
//        city = citye.getText().toString().trim();
//        email = emaile.getText().toString().trim();
//        psw = pswe.getText().toString().trim();
//        //validate data
//        if (TextUtils.isEmpty(name)) {
//            Toast.makeText(this, "Enter Name...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(shopname)) {
//            Toast.makeText(this, "Enter Shop Name...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(phone)) {
//            Toast.makeText(this, "Enter Phone Number...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(city)) {
//            Toast.makeText(this, "Enter City...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Toast.makeText(this, "Invalid Email Address...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "Enter the email Address...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (psw.length() < 6) {
//            Toast.makeText(this, "Password must be at least 6 characters long...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(psw)) {
//            Toast.makeText(this, "Enter Password...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        createAccount();
//    }
//
//    private void createAccount() {
//        AlertDialog progressDialog;
//        progressDialog.setMessage("Creating account");
//        progressDialog.show();
//
//        //create account
//        Object firebaseAuth;
//        firebaseAuth.createUserWithEmailAndPassword(email, psw)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        //account created
//                        saverFirebaseData();
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        //failed creating account
//                        progressDialog.dismiss();
//                        Toast.makeText(RegisterEmp.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//    }
//
//    private void saverFirebaseData() {
//        progressDialog.setMessage("Saving Account Info...");
//
//        String timestamp = "" + System.currentTimeMillis();
//
//
//            //saving info without image;
//            //setup data to save
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("uid", "" + firebaseAuth.getUid());
//            hashMap.put("email", "" + email);
//            hashMap.put("name", "" + name);
//            hashMap.put("shopname", "" + shopname);
//            hashMap.put("phone", "" + phone);
//            hashMap.put("city", "" + city);
//            hashMap.put("psw", "" + psw);
//            hashMap.put("timestamp", "" + timestamp);
//            hashMap.put("accounttype", "Seller");
//            hashMap.put("online", "true");
//            hashMap.put("profileImage", "");
//
//            //save to db
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
//            ref.child(firebaseAuth.getUid()).setValue(hashMap)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            //db updated
//                            progressDialog.dismiss();
//                            startActivity(new Intent(RegisterEmp.this, Seller.class));
//                            finish();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            //failed updating
//                            progressDialog.dismiss();
//                            startActivity(new Intent(RegisterEmp.this, Seller.class));
//                            finish();
//                        }
//                    });
//
//    }
//
//}