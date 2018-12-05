package com.dev.angazaproject;

import android.content.Context;

import com.dev.angazaproject.ui.main.MainViewModel;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    MainViewModel mainViewModel;

    @Before
    void setUp(){
    }
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.dev.angazaproject", appContext.getPackageName());
    }



    @Test
    void getDataTest(){

    }



}
