package com.example.studyhelper_android_firebase.classes;

import java.util.Date;

public class Complain {
    private String complainId;
    private String userID;
    private String Type;
    private String Status;
    private String Date;
    private String content;

    public Complain() {
        // Default constructor needed for call to DataSnapshot.getValue(User.class
    }

    //overloaded constructor
    public Complain(String complainId, String userId, String type, String status, String  date, String complain) {
        this.complainId = complainId;
        this.userID = userId;
        this.Type = type;
        this.Status = status;
        this.Date = date;
        this.content = complain;
    }

    public Complain(String userId, String type, String status, String date, String complain) {
        this.userID = userId;
        this.Type = type;
        this.Status = status;
        this.Date = date;
        this.content = complain;
    }

    public Complain(String type, String date, String complain) {
        this.Type = type;
        this.Date = date;
        this.content = complain;
    }

    //getters and setters
    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {

        this.Date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String complain) {
        this.content = complain;
    }
}
