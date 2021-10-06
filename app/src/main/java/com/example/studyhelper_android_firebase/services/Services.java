package com.example.studyhelper_android_firebase.services;

import android.util.Log;

import com.example.studyhelper_android_firebase.classes.User;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Services {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void sendMail(String id) {
        //needed variables
        String toEmail = "smdnipun@gmail.com"; // receiver email
        String fromEmail = "distributionsmd5@gmail.com"; // sender email
        String password = "smddistributor123@";
        String username = getUsername(id);
        String subject = "Study-Helper Complaint Confirmation";//subject
//        String content = "Dear" + username + ",\nWe have received your complaint and we will resolve it soon are possible.\nThank you."; //content
        String content = "hi";

        //initialize properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
//        properties.put("mail.smtp.socketFactory.port", "465");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");

        //Initialize session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            //initialize email content
            Message message = new MimeMessage(session);
            //Sender email
            message.setFrom(new InternetAddress(fromEmail));
            //Recipient email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            //Email subject
            message.setSubject(subject);
            //Email message
            message.setText(content);

            //send email
//            new SendMail().execute(message);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getEmail(String id) {
        String email = null;
        ArrayList<User> userList = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User u=new User(document.getId(),document.toObject(User.class));
                            userList.add(u);
                        }
                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                });
        for(User u : userList){
            if (id.equals(u.getUser().getId())) {
                email = u.getUser().getEmail();
                return email;
            }
        }
        return null;
    }

    public String getUsername(String id) {
        String username = null;
        ArrayList<User> userList = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User u=new User(document.getId(),document.toObject(User.class));
                            userList.add(u);
                        }
                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                });
        for(User u : userList){
            if (id.equals(u.getUser().getId())) {
                username = u.getUser().getUsername();
                return username;
            }
        }
        return null;
    }

//    private class SendMail extends AsyncTask<Message, String, String> {
//        //initialize progress dialog
//        private ProgressDialog progressDialog;
//
////        @Override
////        protected void onPreExecute() {
////            super.onPreExecute();
////            //create and show progress dialog
////            progressDialog = ProgressDialog.show(MainActivity.this)
////        }
//
//        @Override
//        protected String doInBackground(Message... messages) {
//            try {
//                //When success
//                Transport.send(messages[0]);
//                return "Success";
//            } catch (MessagingException e) {
//                //When error
//                e.printStackTrace();
//                return "Error";
//            }
//        }
//
////        @Override
////        protected void onPostExecute(String s) {
////            super.onPostExecute(s);
////            //Dismiss Progress dialog
////            progressDialog.dismiss();
////            if(s.equals("Success")){
////
////            }
////        }
//    }
}
