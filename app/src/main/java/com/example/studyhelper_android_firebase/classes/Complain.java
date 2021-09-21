package com.example.studyhelper_android_firebase.classes;

public class Complain {
    public String complainId;
    public String userID;
    public String Type;
    public String Status;
    public Long Date;
    public String Complain;

    public Complain() {
        // Default constructor needed for call to DataSnapshot.getValue(User.class
    }

    //overloaded constructor
    public Complain(String complainId, String userId, String type, String status, Long  date, String complain) {
        this.complainId = complainId;
        this.userID = userId;
        this.Type = type;
        this.Status = status;
        this.Date = date;
        this.Complain = complain;
    }

    public Complain(String userId, String type, String status, Long date, String complain) {
        this.userID = userId;
        this.Type = type;
        this.Status = status;
        this.Date = date;
        this.Complain = complain;
    }

    public Complain(String type, long date, String complain) {
        this.Type = type;
        this.Date = date;
        this.Complain = complain;
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

    public Long  getDate() {
        return Date;
    }

    public void setDate(Long  date) {
        this.Date = date;
    }

    public String getComplain() {
        return Complain;
    }

    public void setComplain(String complain) {
        this.Complain = complain;
    }
}
