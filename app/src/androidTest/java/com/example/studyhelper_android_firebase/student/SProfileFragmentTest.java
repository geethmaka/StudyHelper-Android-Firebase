package com.example.studyhelper_android_firebase.student;

import static org.junit.Assert.*;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.Login;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public class SProfileFragmentTest {

    @Rule
    public ActivityTestRule<Login> sprofileActivity = new ActivityTestRule<Login>(Login.class);
    private Login lActivity = null;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}