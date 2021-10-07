package com.example.studyhelper_android_firebase.classes;

public class ICourse {
    private String subject;
    private String stream;
    private boolean availability;

    public ICourse() {
    }

    public ICourse(String subject, String stream, boolean availability) {
        this.subject = subject;
        this.stream = stream;
        this.availability = availability;
    }

    public String getSubject() {
        return subject;
    }

    public String getStream() {
        return stream;
    }

    public boolean isAvailability() {
        return availability;
    }

}
