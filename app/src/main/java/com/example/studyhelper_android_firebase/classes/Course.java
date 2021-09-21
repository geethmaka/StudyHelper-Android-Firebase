package com.example.studyhelper_android_firebase.classes;

public class Course {
    private String subject;
    private int grade;
    private boolean availability;
    private String id;
    private Course course;

    public Course(String subject, int grade, boolean availability) {
        this.subject = subject;
        this.grade = grade;
        this.availability = availability;
    }

    public Course(String id, Course course) {
        this.id = id;
        this.course = course;
    }

    public Course() {
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
