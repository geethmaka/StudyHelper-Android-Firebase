package com.example.studyhelper_android_firebase.complain;

import static org.junit.Assert.*;

import com.example.studyhelper_android_firebase.ComTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComTestTest {
    private static ComTest com;
    private static boolean isNull;

    @BeforeClass
    public static void objCreate() {
        com = new ComTest();
    }

    @Before
    public void setUp() throws Exception {
        isNull = false;
    }

    @Test
    public void NullDetails() {
        isNull = com.nullContent("message");
        assertTrue(String.valueOf(true),isNull);
    }

    @After
    public void tearDown() throws Exception {
        isNull= Boolean.parseBoolean(null);
    }

    @AfterClass
    public static void clearAll() {
        com = null;
    }
}