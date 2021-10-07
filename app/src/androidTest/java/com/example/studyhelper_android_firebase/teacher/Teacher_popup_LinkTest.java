package com.example.studyhelper_android_firebase.teacher;

import static org.junit.Assert.*;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class Teacher_popup_LinkTest {
    @Rule
    public ActivityTestRule<Teacher_popup_Link> linkActivity = new ActivityTestRule<Teacher_popup_Link>(Teacher_popup_Link.class, true, false);
    private Teacher_popup_Link lActivity = null;

    @Before
    public void setUp() throws Exception {
        lActivity = linkActivity.getActivity();
    }

    @Test
    public void testUploadLink() throws Throwable {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        linkActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = linkActivity.getActivity().findViewById(R.id.linkUploder);
                    EditText title = view.findViewById(R.id.link_title);
                    Spinner subject = view.findViewById(R.id.spinner);
                    EditText time = view.findViewById(R.id.Time);
                    EditText link = view.findViewById(R.id.link_add);
                    title.setText("test");
                    subject.setSelection(2);
                    time.setText("test");
                    link.setText("test");
                    assertFalse(linkActivity.getActivity().checkForEmpty(title.getText().toString(), subject.getSelectedItem().toString(), time.getText().toString(), link.getText().toString()));
                }
        );

    }

    @Test
    public void testLaunch() {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        linkActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = linkActivity.getActivity().findViewById(R.id.link_heading);
                    assertNotNull(view);
                }
        );
    }

    @After
    public void tearDown() throws Exception {

    }
}