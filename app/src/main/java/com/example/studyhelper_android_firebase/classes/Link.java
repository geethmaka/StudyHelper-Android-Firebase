package com.example.studyhelper_android_firebase.classes;

//creating link class
public class Link {

    private String Subject;
    private String Title;
    private Long Date;
    private String Time;
    private String Link;
    private String Tid;

    private Link obj;
    private String id;

    public Link() {
    }

    public Link(String tid, String subject, String title, Long date, String time, String link) {
        Tid = tid;
        Subject = subject;
        Title = title;
        Date = date;
        Time = time;
        Link = link;

    }

    public Link(String id, Link obj) {
        this.id = id;
        this.obj = obj;
    }
//getters and setters

    public String getTid() {
        return Tid;
    }

    public Link getObj() {
        return obj;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return Subject;
    }

    public String getTitle() {
        return Title;
    }

    public Long getDate() {
        return Date;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDate(Long date) {
        Date = date;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setLink(String link) {
        Link = link;
    }

    public void setTid(String tid) {
        Tid = tid;
    }


    public String getTime() {
        return Time;
    }

    public String getLink() {
        return Link;
    }

}
