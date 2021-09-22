package com.example.studyhelper_android_firebase.classes;

public class Complain {
    private String complainId;
    private String userID;
    private String type;
    private String status;
    private String date;
    private String content;

    private Complain complain;

    public Complain() {
        // Default constructor needed for call to DataSnapshot.getValue(User.class
    }

    //overloaded constructor

//    public Complain(String complainId, String userId, String type, String status, String date, String content) {
//        this.complainId = complainId;
//        this.userID = userId;
//        this.Type = type;
//        this.Status = status;
//        this.Date = date;
//        this.Content = content;
//    }

    public Complain(String userId, String type, String status, String date, String content) {
        this.userID = userId;
        this.type = type;
        this.status = status;
        this.date = date;
        this.content = content;
    }

//    public Complain(String type, String date, String complain) {
//        this.Type = type;
//        this.Date = date;
//        this.Content = complain;
//    }

    public Complain(String ID, Complain complain) {
        this.complainId = ID;
        this.complain = complain;
    }
    //getters and setters


    public Complain getComplain() {
        return complain;
    }

    public String getComplainId() {
        return complainId;
    }

    public String getUserID() {
        return userID;
    }

    public String getType() {
        return type;
    }


    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

}
