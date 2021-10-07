package com.example.studyhelper_android_firebase.classes;

import java.io.File;

public class Pdf {

    public String subject;
    public String title;
    public String pdf;
    private String id;

    private String Tid;

    private Pdf obj;


    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public void setObj(Pdf obj) {
        this.obj = obj;
    }


    public Pdf(String id, Pdf obj) {
        this.id = id;
        this.obj = obj;
    }

    public Pdf() {
    }

    public Pdf(String subject, String title, String pdf, Pdf obj, String id) {
        this.subject = subject;
        this.title = title;
        this.pdf = pdf;
        this.obj = obj;
        this.id = id;
    }

    public Pdf(String subject, String title, String pdf) {
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

    public Pdf getObj() {
        return obj;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
