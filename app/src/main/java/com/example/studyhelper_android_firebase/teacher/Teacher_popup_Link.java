//package com.example.studyhelper_android_firebase.teacher;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.DatePickerDialog;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//
//import com.example.studyhelper_android_firebase.R;
//import com.example.studyhelper_android_firebase.classes.Link;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.Calendar;
//
//
//public class Teacher_popup_Link extends Activity {
//
//
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//
//
//
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.teacher_popup_link);
//
////            //date picker
////            initDatePicker();
////            dateButton = findViewById(R.id.datePickerButton);
////            dateButton.setText(getTodaysDate());
//
//
//
//
//            //upload data to database
//            Button upload=findViewById(R.id.btn_uploadlink);
//
//            upload.setOnClickListener((View v)->{
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                String Subject =findViewById(R.id.spinner).toString();
//                String Title =findViewById(R.id.link_title).toString();
//                View Date = findViewById(R.id.calendarView);
//                String Time =findViewById(R.id.Time).toString();
//                String AmPm = findViewById(R.id.spinner2).toString();
//                String Link = findViewById(R.id.link_add).toString();
//
//                Link link=new Link(Subject,Title,Date.toString(),Time,AmPm,Link);
//
//                db.collection("link")
//                        .add(link)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w("TAG", "Error adding document", e);
//                            }
//                        });
//            });
//
//        }
//
////    private DatePickerDialog datePickerDialog;
////    private Button dateButton;
////
////
////    private String getTodaysDate()
////    {
////        Calendar cal = Calendar.getInstance();
////        int year = cal.get(Calendar.YEAR);
////        int month = cal.get(Calendar.MONTH);
////        month = month + 1;
////        int day = cal.get(Calendar.DAY_OF_MONTH);
////        return makeDateString(day, month, year);
////    }
////
////    private void initDatePicker()
////    {
////        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
////        {
////            @Override
////            public void onDateSet(DatePicker view, int year, int month, int day) {
////                month = month + 1;
////                String date = makeDateString(day, month, year);
////                dateButton.setText(date);
////            }
////
////
////        };
////
////        Calendar cal = Calendar.getInstance();
////        int year = cal.get(Calendar.YEAR);
////        int month = cal.get(Calendar.MONTH);
////        int day = cal.get(Calendar.DAY_OF_MONTH);
////
////        int style = AlertDialog.THEME_HOLO_LIGHT;
////
////        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
////        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
////
////    }
////
////    private String makeDateString(int day, int month, int year)
////    {
////        return getMonthFormat(month) + " " + day + " " + year;
////    }
////
////    private String getMonthFormat(int month)
////    {
////        if(month == 1)
////            return "JAN";
////        if(month == 2)
////            return "FEB";
////        if(month == 3)
////            return "MAR";
////        if(month == 4)
////            return "APR";
////        if(month == 5)
////            return "MAY";
////        if(month == 6)
////            return "JUN";
////        if(month == 7)
////            return "JUL";
////        if(month == 8)
////            return "AUG";
////        if(month == 9)
////            return "SEP";
////        if(month == 10)
////            return "OCT";
////        if(month == 11)
////            return "NOV";
////        if(month == 12)
////            return "DEC";
////
////        //default should never happen
////        return "JAN";
////    }
////
////    public void openDatePicker(View view)
////    {
//////        new datePickerDialog.setVisible(true);
////    }
//
//
//
//    }
//
