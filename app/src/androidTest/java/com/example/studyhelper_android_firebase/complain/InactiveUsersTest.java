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

public class InactiveUsersTest {

    @Rule
    public ActivityTestRule<InactiveUsers> inactiveUser = new ActivityTestRule<InactiveUsers>(InactiveUsers.class, true, false);
    public InactiveUsers inactive = null;

    @Before
    public void setUp() throws Exception {
        inactive = inactiveUser.getActivity();
    }

    @Test
    public void testLaunch() {
        Intent intent = new Intent();

        inactiveUser.launchActivity(intent).runOnUiThread(() -> {
            View view = inactiveUser.getActivity().findViewById(R.id.tv_inactiveUsers);
            Assert.assertNotNull(view);
        });
    }

    @Test
    public void testRecycleView() {
        Intent intent = new Intent();

        inactiveUser.launchActivity(intent).runOnUiThread(() -> {
            View view = inactiveUser.getActivity().findViewById(R.id.RVinactiveUser);
            Assert.assertNotNull(view);
        });
    }

    @After
    public void tearDown() throws Exception {
        inactive = null;
    }
}