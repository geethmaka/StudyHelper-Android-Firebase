package com.example.studyhelper_android_firebase.course;

import static org.junit.Assert.*;

import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ReportFragment;
import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Course_manager_homeTest {
    public ActivityTestRule<Course_manager_home> courseTest = new ActivityTestRule<Course_manager_home>(Course_manager_home.class);
    private Course_manager_home activity = null;
    @Before
    public void setUp() throws Exception {
        activity= courseTest.getActivity();
    }

    @Test
    public void testLaunch(){
View view = activity.findViewById(R.layout.course_manager_home);
assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
    }

}