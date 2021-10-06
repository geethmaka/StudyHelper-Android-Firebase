package com.example.studyhelper_android_firebase.teacher;

import static org.junit.Assert.*;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.Login;
import com.example.studyhelper_android_firebase.LoginTest;
import com.example.studyhelper_android_firebase.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TeacherMainActivityTest {

    @Rule
    public ActivityTestRule<TeacherMainActivity> teacherMainActivity=new ActivityTestRule<TeacherMainActivity>(TeacherMainActivity.class,true, false);
    private  TeacherMainActivity tMainActivity = null;

    @Before
    public void setUp() throws Exception {
        tMainActivity = teacherMainActivity.getActivity();
        }

    @Test
    public  void  testLaunch(){

        Intent intent = new Intent();
        teacherMainActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = teacherMainActivity.getActivity().findViewById(R.id.tmbottomNav);
                    assertNotNull(view);
                }
        );
    }
    @After
    public void tearDown() throws Exception {

        tMainActivity=null;

    }
}
