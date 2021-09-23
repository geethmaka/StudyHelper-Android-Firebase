package com.example.studyhelper_android_firebase.classes;

public class IPdf {

     public String subject;
     public String title;
     public String pdf;
     public String tid;

    public IPdf() {
    }

    public IPdf(String id,String subject, String title, String pdf) {
        this.tid = id;
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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
