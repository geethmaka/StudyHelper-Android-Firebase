package com.example.studyhelper_android_firebase.complain;

public class Cal {
    public int getPercentage(int count, int total) {
        int per = 0;
        per = Math.round((float) count/total * 100);
        return per;
    }
}

