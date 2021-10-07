package com.example.studyhelper_android_firebase.complain;

import static org.junit.Assert.*;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ResolvedComplaintsTest {

    @Rule
    public ActivityTestRule<ResolvedComplaints> rComplain = new ActivityTestRule<ResolvedComplaints>(ResolvedComplaints.class, true, false);
    public ResolvedComplaints resolved = null;

    @Before
    public void setUp() throws Exception {
        resolved = rComplain.getActivity();
    }

    @Test
    public void testLaunch() {
        Intent intent = new Intent();

        rComplain.launchActivity(intent).runOnUiThread(() -> {
            View view = rComplain.getActivity().findViewById(R.id.tv_resolved);
            Assert.assertNotNull(view);
        });
    }

    @Test
    public void testRecycleView() {
        Intent intent = new Intent();

        rComplain.launchActivity(intent).runOnUiThread(() -> {
            View view = rComplain.getActivity().findViewById(R.id.RVresolvedComplaints);
            Assert.assertNotNull(view);
        });
    }

    @After
    public void tearDown() throws Exception {
        resolved = null;
    }
}