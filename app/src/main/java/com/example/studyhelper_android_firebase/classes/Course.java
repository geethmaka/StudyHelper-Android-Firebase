package com.example.studyhelper_android_firebase.classes;

public class Course {
    private String subject;
    private String stream;
    private boolean availability;
    private String id;
    private Course course;

    public Course(String subject, String stream, boolean availability) {
        this.subject = subject;
        this.stream = stream;
        this.availability = availability;
    }

    public Course(String id, Course course) {
        this.id = id;
        this.course = course;
    }

    public Course() {
    }

    public String getStream() {
        return stream;
    }

    public Course getCourse() {
        return course;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isAvailability() {
        return availability;
    }

}
