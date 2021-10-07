package com.example.studyhelper_android_firebase.complain;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class NewComplaintTest {

    @Rule
    public ActivityTestRule<NewComplaint> newComplain = new ActivityTestRule<NewComplaint>(NewComplaint.class, true, false);
    private NewComplaint nComplain = null;

    @Before
    public void setUp() throws Exception {
        nComplain = newComplain.getActivity();
    }

    @Test
    public void testLaunch() {
        Intent intent = new Intent();

        newComplain.launchActivity(intent).runOnUiThread(() -> {
            View view = newComplain.getActivity().findViewById(R.id.newcomp);
            Assert.assertNotNull(view);
        });
    }

    @Test
    public void testRecycleView() {
        Intent intent = new Intent();

        newComplain.launchActivity(intent).runOnUiThread(() -> {
                    View view = newComplain.getActivity().findViewById(R.id.RVnewComplains);
                    Assert.assertNotNull(view);
                }
        );
    }

    @After
    public void tearDown() throws Exception {
        nComplain = null;
    }

}