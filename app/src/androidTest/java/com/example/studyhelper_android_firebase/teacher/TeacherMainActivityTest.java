package com.example.studyhelper_android_firebase.teacher;

import static org.junit.Assert.*;

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
    public ActivityTestRule<TeacherMainActivity> teacherMainActivity=new ActivityTestRule<TeacherMainActivity>(TeacherMainActivity.class);
    public ActivityTestRule<Login> loginActivity=new ActivityTestRule<Login>(Login.class);
    private  TeacherMainActivity tMainActivity = null;
    private Login lActivity=null;

    @Before
    public void setUp() throws Exception {
        tMainActivity = teacherMainActivity.getActivity();
        lActivity = loginActivity.getActivity();

        }

    @Test
    public  void  testLaunch(){

        View view= tMainActivity.findViewById(R.layout.teacher_details);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {

        tMainActivity=null;

    }
}
