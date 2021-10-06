package com.example.studyhelper_android_firebase.complain;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class NewComplaintTest extends TestCase {

    @Rule
    public ActivityTestRule<NewComplaint> newComplain = new ActivityTestRule<NewComplaint>(NewComplaint.class);
    private  NewComplaint nComplain = null;

    @Before
    public void setUp() throws Exception {
        nComplain=newComplain.getActivity();
    }

    @Test
    public void testLaunch(){
        View view= nComplain.findViewById(R.layout.complain_new_complain);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        nComplain=null;
    }

}