package com.example.studyhelper_android_firebase.classes;

import java.io.File;

public class Pdf {

     public String subject;
     public String title;
     public String pdf;

    public Pdf() {
    }

    public Pdf(String subject, String title, String pdf) {
        this.subject = subject;
        this.title = title;
        this.pdf = pdf;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
