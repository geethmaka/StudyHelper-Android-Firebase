package com.example.studyhelper_android_firebase;

import static org.junit.Assert.*;

import android.app.Instrumentation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivity = new ActivityTestRule<Login>(Login.class);
    private Login lActivity = null;

    @Before
    public void setUp() throws Exception {
        lActivity = loginActivity.getActivity();
    }

    @Test
    public void loadLogin() {
        View view = lActivity.findViewById(R.id.loginLayout);
        assertNotNull("Login Loaded", view);
    }

    @Test
    public void testLaunch() {
        lActivity.runOnUiThread(() -> {
            View view = lActivity.findViewById(R.id.loginLayout);
            EditText email = view.findViewById(R.id.editTextEmail);
            EditText pass = view.findViewById(R.id.editTextPassword);
            email.setText("c");
            pass.setText("c");
            Button loginBtn = view.findViewById(R.id.cirLoginButton);
            loginBtn.performClick();
            assertFalse(lActivity.isCurUserLoggedIn());
        });
    }

    @Test
    public void testInvalidLogin() {
        lActivity.runOnUiThread(() -> {
            View view = lActivity.findViewById(R.id.loginLayout);
            EditText email = view.findViewById(R.id.editTextEmail);
            EditText pass = view.findViewById(R.id.editTextPassword);
            email.setText("geethmaka@gmail.com");
            pass.setText("gerreth");
            Button loginBtn = view.findViewById(R.id.cirLoginButton);
            loginBtn.performClick();
            assertFalse(lActivity.isCurUserLoggedIn());
        });
    }

    @After
    public void tearDown() throws Exception {

    }

}