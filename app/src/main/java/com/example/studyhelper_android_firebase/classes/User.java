package com.example.studyhelper_android_firebase.classes;

public class User {
    public String id;
    public String username;
    public String type;
    public String stream;
    public boolean status;
    public String password;
    public String mobile;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String id, String username, String type, String stream, boolean status, String password, String mobile, String email) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.stream = stream;
        this.status = status;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
    }

    public User(String username, String type, String stream, boolean status, String password, String mobile, String email) {
        this.username = username;
        this.type = type;
        this.stream = stream;
        this.status = status;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}