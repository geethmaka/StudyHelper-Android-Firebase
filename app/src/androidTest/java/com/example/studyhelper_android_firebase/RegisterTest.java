package com.example.studyhelper_android_firebase;

import static org.junit.Assert.*;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RegisterTest {
    @Rule
    public ActivityTestRule<Register> RegisterActivity = new ActivityTestRule<Register>(Register.class);
    private Register rActivity = null;

    @Before
    public void setUp() throws Exception {
        rActivity = RegisterActivity.getActivity();
    }

    @Test
    public void testLaunchheading() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.regheading);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchname() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.reg_name);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchspinnertype() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.reg_type);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchspinnerstream() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.reg_Stream);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchmn() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.reg_mn);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchemail() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.reg_email);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchpw() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.reg_pw);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchbtn() {
        rActivity.runOnUiThread(() -> {
            View view = RegisterActivity.getActivity().findViewById(R.id.reg_btn);
            assertNotNull(view);
        });
    }


    @Test
    public void testUploadLink() throws Throwable {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        rActivity.runOnUiThread(() -> {
                    View view = rActivity.findViewById(R.id.register);
                    EditText reg_name = rActivity.findViewById(R.id.reg_name);
                    EditText reg_mn = rActivity.findViewById(R.id.reg_mn);
                    Spinner reg_type = rActivity.findViewById(R.id.reg_type);
                    Spinner reg_Stream = rActivity.findViewById(R.id.reg_Stream);
                    EditText reg_email = rActivity.findViewById(R.id.reg_email);
                    EditText reg_pw = rActivity.findViewById(R.id.reg_pw);
                    reg_name.setText("test");
                    reg_mn.setText("test");
                    reg_type.setSelection(1);
                    reg_Stream.setSelection(1);
                    reg_email.setText("test");
                    reg_pw.setText("test");
                    assertFalse(rActivity.checkForEmpty(reg_name.getText().toString(), reg_mn.getText().toString(), reg_type.getSelectedItem().toString(), reg_Stream.getSelectedItem().toString(), reg_email.getText().toString(), reg_pw.getText().toString()));
                }
        );

    }


    @After
    public void tearDown() throws Exception {
    }
}