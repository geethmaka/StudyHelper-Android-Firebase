package com.example.studyhelper_android_firebase;

import static org.junit.Assert.*;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivity=new ActivityTestRule<Login>(Login.class);
    private Login lActivity=null;

    @Before
    public void setUp() throws Exception {
        lActivity=loginActivity.getActivity();
    }

    @Test
    public void testLaunch(){
        View view1= lActivity.findViewById(R.id.editTextEmail);
        View view2=lActivity.findViewById(R.id.editTextPassword);
        assertNotNull(view1);
        assertNotNull(view2);

    }

    @After
    public void tearDown() throws Exception {
        lActivity=null;

    }
}