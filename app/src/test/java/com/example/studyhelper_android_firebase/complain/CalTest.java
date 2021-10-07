package com.example.studyhelper_android_firebase.complain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalTest {
    private static Cal cal;

    @BeforeClass
    public static void objCreate() {
        cal = new Cal();
    }

    @Before
    public void setUp() {

    }

    @Test
    public void percentageTest() {
        int input1 = 30;
        int input2 = 60;
        int expected = 50;
        int output;
        output = cal.getPercentage(input1, input2);
        assertEquals(expected, output);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void clearAll() {
        cal = null;
    }
}