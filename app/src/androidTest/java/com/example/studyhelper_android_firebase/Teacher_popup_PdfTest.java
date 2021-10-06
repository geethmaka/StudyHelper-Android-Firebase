package com.example.studyhelper_android_firebase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

public class Teacher_popup_PdfTest {
    @Rule
    public ActivityTestRule<Teacher_popup_Pdf> pdfActivity = new ActivityTestRule<Teacher_popup_Pdf>(Teacher_popup_Pdf.class, true, false);
    private Teacher_popup_Pdf pActivity = null;

    @Before
    public void setUp() throws Exception {
        pActivity = pdfActivity.getActivity();
    }

    @Test
    public void testUploadPdf() throws Throwable {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        pdfActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = pdfActivity.getActivity().findViewById(R.id.pdfUploder);
                    EditText title = view.findViewById(R.id.editTextPdf);
                    Spinner subject = view.findViewById(R.id.spinnerpdf);
                    TextView notify=view.findViewById(R.id.notifyPdf);
                    title.setText("test");
                    subject.setSelection(2);
                    notify.setText("test");

                    assertFalse(pdfActivity.getActivity().checkForEmpty(title.getText().toString(), subject.getSelectedItem().toString() ,notify.getText().toString()));
                }
        );

    }


    @Test
    public void testLaunchHeading() {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        pdfActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = pdfActivity.getActivity().findViewById(R.id.pdf_heading);
                    assertNotNull(view);
                }
        );
    }

    @Test
    public void testLaunchSpinner() {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        pdfActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = pdfActivity.getActivity().findViewById(R.id.spinnerpdf);
                    assertNotNull(view);
                }
        );
    }

    @Test
    public void testLaunchTitleEditText() {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        pdfActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = pdfActivity.getActivity().findViewById(R.id.editTextPdf);
                    assertNotNull(view);
                }
        );
    }

    @Test
    public void testLaunchSelectPdf() {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        pdfActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = pdfActivity.getActivity().findViewById(R.id.selectPdf);
                    assertNotNull(view);
                }
        );
    }

    @Test
    public void testLaunchNotifyTextBox() {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        pdfActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = pdfActivity.getActivity().findViewById(R.id.notifyPdf);
                    assertNotNull(view);
                }
        );
    }

    @Test
    public void testLaunchUploadButton() {
        Intent intent = new Intent();
        intent.putExtra("uid", "VUORF7G4KHXZNcsX6W7Q");

        pdfActivity.launchActivity(intent).runOnUiThread(() -> {
                    View view = pdfActivity.getActivity().findViewById(R.id.btn_uplodpdf);
                    assertNotNull(view);
                }
        );
    }

    @After
    public void tearDown() throws Exception {
    }
}