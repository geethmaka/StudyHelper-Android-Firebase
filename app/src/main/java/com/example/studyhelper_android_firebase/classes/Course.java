package com.example.studyhelper_android_firebase.classes;

public class Course {
    private String subject;
    private int grade;
    private boolean availability;

    public Course(String subject, int grade, boolean availability) {
        this.subject = subject;
        this.grade = grade;
        this.availability = availability;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
