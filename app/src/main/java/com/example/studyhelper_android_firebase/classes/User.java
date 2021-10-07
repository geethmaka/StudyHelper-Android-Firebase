package com.example.studyhelper_android_firebase.classes;

public class User {
    private String id;
    private String username;
    private String type;
    private String stream;
    private String status;
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

//    public User(String id, String username, String type, String stream, boolean status, String password, long mobile, String email) {
//        this.id = id;
//        this.username = username;
//        this.type = type;
//        this.stream = stream;
//        this.status = status;
//        this.password = password;
//        this.mobile = mobile;
//        this.email = email;
//    }

    public User(String username, String type, String stream, String status, String password, long mobile, String email) {
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

//    public User(String username,Long mobile, String email) {
//
//        this.username = username;
//        this.mobile = mobile;
//        this.email = email;
//
//    }

    public User(String username,  Long mobile, String email, String stream) {

        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.stream = stream;


    }

    public User getUser(){
        return this.user;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public String getStream() {
        return stream;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public long getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

}