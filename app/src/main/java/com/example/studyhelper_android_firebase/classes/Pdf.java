package com.example.studyhelper_android_firebase.classes;

import java.io.File;

public class Pdf {

     public String subject;
     public String title;
     public String pdf;
    private Link obj;
    private String id;

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public void setObj(Pdf obj) {
        this.obj = obj;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String Tid;

     private Pdf obj;
     private String id;

    public Pdf getObj() {
        return obj;
    }

    public String getId() {
        return id;
    }

    public Pdf(String id, Pdf obj){
         this.id=id;
         this.obj=obj;
     }

    public Pdf() {
    }

    public Pdf(String subject, String title, String pdf,Link obj,String id) {
        this.subject = subject;
        this.title = title;
        this.pdf = pdf;
        this.obj=obj;
        this.id=id;
    }

    public Pdf(String  subject, String title, String pdf) {
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

    public Link getObj() {
        return obj;
    }

    public void setObj(Link obj) {
        this.obj = obj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
