package com.example.studyhelper_android_firebase.classes;

public class Complain {
    public String complainId;
    public String userId;
    public String type;
    public String status;
    public String date;
    public String complain;

    public Complain() {
        // Default constructor needed for call to DataSnapshot.getValue(User.class
    }

    //overloaded constructor
    public Complain(String complainId, String userId, String type, String status, String date, String complain) {
        this.complainId = complainId;
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.date = date;
        this.complain = complain;
    }

    public Complain(String userId, String type, String status, String date, String complain) {
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.date = date;
        this.complain = complain;
    }

    //getters and setters
    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }
}
