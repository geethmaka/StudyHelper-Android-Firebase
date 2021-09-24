package com.example.studyhelper_android_firebase.classes;

public class iUser {
    private String username;
    private String type;
    private String stream;
    private String status;
    private String password;
    private long mobile;
    private String email;

    public iUser() {
    }

    public iUser(String username, String type, String stream, String status, String password, long mobile, String email) {
        this.username = username;
        this.type = type;
        this.stream = stream;
        this.status = status;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
