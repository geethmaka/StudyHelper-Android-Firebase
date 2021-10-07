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

public class ActiveUsersTest {

    @Rule
    public ActivityTestRule<ActiveUsers> activeUsers = new ActivityTestRule<ActiveUsers>(ActiveUsers.class, true, false);
    private ActiveUsers active = null;

    @Before
    public void setUp() throws Exception {
        active = activeUsers.getActivity();
    }

    @Test
    public void testLaunch() {
        Intent intent = new Intent();

        activeUsers.launchActivity(intent).runOnUiThread(() -> {
                    View view = activeUsers.getActivity().findViewById(R.id.tv_activeUser);
                    Assert.assertNotNull(view);
                }
        );
    }

    @Test
    public void testRecycleView() {
        Intent intent = new Intent();

        activeUsers.launchActivity(intent).runOnUiThread(() -> {
                    View view = activeUsers.getActivity().findViewById(R.id.RVactiveUser);
                    Assert.assertNotNull(view);
                }
        );
    }

    @After
    public void tearDown() throws Exception {
        active = null;
    }
}