package com.example.studyhelper_android_firebase.student;

import static org.junit.Assert.*;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;
import com.example.studyhelper_android_firebase.Register;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class Student_complaintTest {
    @Rule
    public ActivityTestRule<Student_complaint> ComplainActivity = new ActivityTestRule<Student_complaint>(Student_complaint.class);
    private Student_complaint csActivity = null;

    @Before
    public void setUp() throws Exception {
        csActivity = ComplainActivity.getActivity();
    }

    @Test
    public void testLaunchdate() {
        csActivity.runOnUiThread(() -> {
            View view = ComplainActivity.getActivity().findViewById(R.id.btn_scvupdate);
            assertNotNull(view);
        });
    }

    @After
    public void tearDown() throws Exception {
    }
}