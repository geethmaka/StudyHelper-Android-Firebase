package com.example.studyhelper_android_firebase.services;

import android.app.ProgressDialog;
import android.os.AsyncTask;

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

    public void sendMail(String id,String subject,String content) {
        //needed variables
        String fromEmail = "distributionsmd5@gmail.com"; // sender email
        String password = "smddistributor123@";

        //initialize properties
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com"); // smtp server
        properties.setProperty("mail.smtp.port", "465"); // port number
        properties.setProperty("mail.smtp.auth", "true"); // Authentication
        properties.setProperty("mail.smtp.socketFactory.port", "465"); // SSL port
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL properties

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
                    InternetAddress.parse(id));
            //Email subject
            message.setSubject(subject);
            //Email message
            message.setText(content);

            //send email
            new SendMail().execute(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class SendMail extends AsyncTask<Message, String, String> {
        //initialize progress dialog
        private ProgressDialog progressDialog;

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //When success
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                //When error
                e.printStackTrace();
                return "Error";
            }
        }

    }
}
