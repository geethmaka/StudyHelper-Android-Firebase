package com.example.studyhelper_android_firebase.complain;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.classes.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ActiveUsers extends AppCompatActivity {

    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //defining the variables
    ProgressDialog progressDialog;
    ArrayList<User> userArrayList;
    Adapter_activeUser userAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_active_users);

        //creating progress dialog until fetching the data
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching the Data...");
        progressDialog.show();

        //defining the variables
        recyclerView = findViewById(R.id.RVactiveUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //initialize the array list
        userArrayList = new ArrayList<User>();
        //initialize the adapter
        userAdapter = new Adapter_activeUser(this,userArrayList);
        recyclerView.setAdapter(userAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("users").orderBy("username", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    if(error != null) {
                        //dismiss progress dialog
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore Error",error.getMessage());
                        return;
                    }

                    //fetching the data from the firestore database
                    for(DocumentChange dc : value.getDocumentChanges()){
                        User n = new User(dc.getDocument().getId(), dc.getDocument().toObject(User.class));
                        if(dc.getType() == DocumentChange.Type.ADDED && n.getUser().getStatus().equals("active")) {
                                userArrayList.add(n);
                        }
                        //dismiss progress dialog
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                    //initialize the adapter
                    userAdapter = new Adapter_activeUser(this,userArrayList);
                    recyclerView.setAdapter(userAdapter);
                    userAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.active_inactive_user_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search User");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //get a new text input
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}