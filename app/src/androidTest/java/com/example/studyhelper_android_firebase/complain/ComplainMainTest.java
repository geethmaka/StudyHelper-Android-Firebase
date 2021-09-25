package com.example.studyhelper_android_firebase.complain;

import static org.junit.Assert.*;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.teacher.TeacherMainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ComplainMainTest {

    @Rule
    public ActivityTestRule<ComplainMain> complainMain=new ActivityTestRule<ComplainMain>(ComplainMain.class);
    private  ComplainMain cpMainActivity = null;

    @Before
    public void setUp() throws Exception {

        cpMainActivity=complainMain.getActivity();
    }

   @Test
    public void testLaunch(){

       View view= cpMainActivity.findViewById(R.layout.fragment_ch_dashboard);
       assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        cpMainActivity=null;
    }

}