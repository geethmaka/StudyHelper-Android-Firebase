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
            View view  = RegisterActivity.getActivity().findViewById(R.id.regheading);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchname() {
        rActivity.runOnUiThread(() -> {
            View view  = RegisterActivity.getActivity().findViewById(R.id.reg_name);
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
            View view  = RegisterActivity.getActivity().findViewById(R.id.reg_Stream);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchmn() {
        rActivity.runOnUiThread(() -> {
            View view  = RegisterActivity.getActivity().findViewById(R.id.reg_mn);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchemail() {
        rActivity.runOnUiThread(() -> {
            View view  = RegisterActivity.getActivity().findViewById(R.id.reg_email);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchpw() {
        rActivity.runOnUiThread(() -> {
            View view  = RegisterActivity.getActivity().findViewById(R.id.reg_pw);
            assertNotNull(view);
        });
    }

    @Test
    public void testLaunchbtn() {
        rActivity.runOnUiThread(() -> {
            View view  = RegisterActivity.getActivity().findViewById(R.id.reg_btn);
            assertNotNull(view);
        });
    }

//    @Test
//    public void testUploadPdf() throws Throwable {
//
//        rActivity.runOnUiThread(() -> {
//                    View view = rActivity.getActivity().findViewById(R.id.pdfUploder);
//                    EditText title = view.findViewById(R.id.editTextPdf);
//                    Spinner subject = view.findViewById(R.id.spinnerpdf);
//                    TextView notify=view.findViewById(R.id.notifyPdf);
//                    title.setText("test");
//                    subject.setSelection(2);
//                    notify.setText("test");
//                    assertFalse(rActivity.getActivity().checkForEmpty(title.getText().toString(), subject.getSelectedItem().toString() ,notify.getText().toString()));
//                }
//        );
//
//    }


    @After
    public void tearDown() throws Exception {
    }
}