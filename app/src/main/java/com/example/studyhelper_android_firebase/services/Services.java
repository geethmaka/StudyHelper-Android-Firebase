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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Services {
    //creating an instance of the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void sendMail(String id) {
        //needed variables
        String toEmail = getEmail(id); // receiver email
        String fromEmail = "distributionsmd5@gmail.com"; // sender email
        String password = "smddistributor123@";

        //initialize properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

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
}
