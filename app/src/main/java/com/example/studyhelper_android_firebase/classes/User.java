package com.example.studyhelper_android_firebase.classes;

public class User {
    private String id;
    private String username;
    private String type;
    private String stream;
    private boolean status;
    private String password;
    private long mobile;
    private String email;

    private User user;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String id, String username, String type, String stream, boolean status, String password, long mobile, String email) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.stream = stream;
        this.status = status;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
    }

    public User(String username, String type, String stream, boolean status, String password, long mobile, String email) {
        this.username = username;
        this.type = type;
        this.stream = stream;
        this.status = status;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
    }

    public User(String id,User user){
        this.id=id;
        this.user=user;
    }

    public User getUser(){
        return this.user;
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

    public void setPassword(String password) { this.password = password; }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) { this.mobile = mobile; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}