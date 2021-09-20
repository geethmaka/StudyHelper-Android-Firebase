package com.example.studyhelper_android_firebase.classes;

import java.io.File;

public class Pdf {

     public String subject;
     public String title;
     public File uppdf;

    public Pdf(String subject, String title, File pdf) {
        this.subject = subject;
        this.title = title;
        this.uppdf = pdf;
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

    public File getPdf() {
        return uppdf;
    }

    public void setPdf(File pdf) {
        this.uppdf = pdf;
    }
}
