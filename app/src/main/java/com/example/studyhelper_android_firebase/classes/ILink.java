package com.example.studyhelper_android_firebase.classes;

//creating link class
public class ILink {

    private String Subject;
    private String Title;
    private Long Date;
    private String Time;
    private String Link;
    private String tid;


    public ILink() {
    }

    public ILink(String id,String subject, String title, Long date, String time, String link) {
        tid=id;
        Subject = subject;
        Title = title;
        Date = date;
        Time = time;
        Link = link;

    }

//getters and setters
public String getTId(){return tid;}

    public String getSubject() {
        return Subject;
    }


    public String getTitle() {
        return Title;
    }


    public Long getDate() {
        return Date;
    }


    public String getTime() {
        return Time;
    }


    public String getLink() {
        return Link;
    }

}
