package com.example.studyhelper_android_firebase.classes;

public class IComplain {

    private String userID;
    private String Type;
    private String Status;
    private String Date;
    private String Content;

    public IComplain() {
        // Default constructor needed for call to DataSnapshot.getValue(User.class
    }
    public IComplain(String userId, String type, String status, String date, String content) {
        this.userID = userId;
        this.Type = type;
        this.Status = status;
        this.Date = date;
        this.Content = content;
    }

    public String getUserID() {
        return userID;
    }

    public String getType() {
        return Type;
    }

    public String getStatus() {
        return Status;
    }

    public String getDate() {
        return Date;
    }

    public String getContent() {
        return Content;
    }
}
