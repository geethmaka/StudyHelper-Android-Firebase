package com.example.studyhelper_android_firebase.course;

import static org.junit.Assert.*;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.result.ActivityResult;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.ActivityTestRule;

import com.example.studyhelper_android_firebase.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

public class UpdateCourseTest {
    @Rule
    public ActivityTestRule<UpdateCourse> updateActivity = new ActivityTestRule<UpdateCourse>(UpdateCourse.class,true,false);
    private UpdateCourse uActivity = null;


    @Before
    public void setUp() throws Exception {
        uActivity = updateActivity.getActivity();
    }

    @Test
    public void testUpdate() throws Throwable {
        Intent intent = new Intent();
        intent.putExtra("id", "Nnfo4UpuBdywmujNtSVf");

        updateActivity.launchActivity(intent).runOnUiThread(()->{
            View view =updateActivity.getActivity().findViewById(R.id.updateDetailsLayout);
            EditText subjectName = view.findViewById(R.id.updateSubject);
            Spinner stream = view.findViewById(R.id.updateStream);
            subjectName.setText("c");
            stream.setSelection(2);
            assertFalse(updateActivity.getActivity().checkForEmpty(subjectName.getText().toString(),stream.getSelectedItem().toString()));
                }
        );
    }
    @Test
    public void UpdateLaunch() {
        Intent intent = new Intent();
        intent.putExtra("id", "Nnfo4UpuBdywmujNtSVf");

        updateActivity.launchActivity(intent).runOnUiThread(()->{
            View view = updateActivity.getActivity().findViewById(R.id.relativeLayout);
            assertNotNull(view);
                }
        );

    }
    @After
    public void tearDown() throws Exception {

    }

}