//package com.example.studyhelper_android_firebase.student;
//
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import java.util.ArrayList;
//
//public class Seller extends AppCompatActivity {
//
//    private TextView sname, sshop, tabprotv, tabordtv, FilteredProducts ,filteredOrdersTv;
//    private ImageButton logout, addpro, filbtn,filterOrderBtn,moreBtn;
//    private ImageView seller;
//    private RelativeLayout ordersr1, productsr1;
//    private EditText searchet;
//    private RecyclerView productrv,ordersRv;
//
//    private ProgressDialog progressDialog;
//
//    private FirebaseAuth firebaseAuth;
////compalin class
//    private ArrayList<ModelProduct> productList;
//    private AdapterProductSeller adapterProductSeller;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        getSupportActionBar().hide();//this line hide action bar
//        setContentView(R.layout.activity_seller);
//
//
//        progressDialog= new ProgressDialog(this);
//        progressDialog.setTitle("Please Wait...");
//        progressDialog.setCanceledOnTouchOutside(false);
//
//        firebaseAuth=FirebaseAuth.getInstance();
//        checkUser();
//        loadAllProducts();
//        loadAllOrders();
//
//        showProductsUI();
//
//
//
//    private void loadFilteredProducts(String selected) {
//        productList= new ArrayList<>();
//        //get all products
//
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
//        reference.child(firebaseAuth.getUid()).child("Products")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //before getting reset list
//                        productList.clear();
//                        for(DataSnapshot ds: snapshot.getChildren()){
//
//                            String procatog= ""+ds.child("catog").getValue();
//
//                            //if selected catogory matches product category then add in list
//                            if(selected.equals(procatog)){
//                                ModelProduct modelProduct= ds.getValue(ModelProduct.class);
//                                productList.add(modelProduct);
//                            }
//                        }
//                        //setup adapter
//                        adapterProductSeller = new AdapterProductSeller(Seller.this, productList);
//                        //set adapter
//                        productrv.setAdapter(adapterProductSeller);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//    }
//
//    private void loadAllProducts() {
//        productList= new ArrayList<>();
//        //get all products
//
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
//        reference.child(firebaseAuth.getUid()).child("Products")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //before getting reset list
//                        productList.clear();
//                        for(DataSnapshot ds: snapshot.getChildren()){
//                            ModelProduct modelProduct= ds.getValue(ModelProduct.class);
//                            productList.add(modelProduct);
//                        }
//                        //setup adapter
//                        adapterProductSeller = new AdapterProductSeller(Seller.this, productList);
//                        //set adapter
//                        productrv.setAdapter(adapterProductSeller);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }
//
//
//
//
//    }
//
//
//
//    }
//
//
//}
