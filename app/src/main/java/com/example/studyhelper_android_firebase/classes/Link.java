package com.example.studyhelper_android_firebase.classes;
//creating link class
public class Link {

    private String LinkID;
                private String Subject;
                        private String Title;
                        private Long Date;
                        private String Time;
//                        private String AmPm;
                        private String Link;

//overload constructor
//                        public Link( String subject, String title, Long date, String time, String amPm, String link) {
//
//        Subject = subject;
//        Title = title;
//        Date = date;
//        Time = time;
//        AmPm = amPm;
//        Link = link;
//    }

    public Link(){}

    public Link(String subject, String title, Long date, String time, String link) {
        Subject = subject;
        Title = title;
        Date = date;
        Time = time;
        Link = link;

    }

//getters and setters

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

//    public String getAmPm() {
//        return AmPm;
//    }
//
//    public void setAmPm(String amPm) {
//        AmPm = amPm;
//    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
