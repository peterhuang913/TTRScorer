package com.peterhuang.ttrscorer;

import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.SingleLaunchActivityTestCase;
import android.test.UiThreadTest;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by peterhuang on 5/27/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        mainActivity = getActivity();
    }

    public void testPreconditions() {
        Log.d("MainActivityTest", "testPreconditions");
        assertNotNull("mainActivity is null", mainActivity);
    }

    @UiThreadTest
    public void testChangeScore() throws Exception {
        Log.d("MainActivityTest", "testChangeScore");
        TextView view = (TextView) mainActivity.findViewById(R.id.score1);
        assertEquals("0", view.getText().toString());
        mainActivity.changeScore(0, 10);
        assertEquals("10", view.getText().toString());
        int[] pos = new int[2];
        view.getLocationOnScreen(pos);
        view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, pos[0], pos[1], 0));
        assertEquals(0, mainActivity.getSelected(), 0);
    }
}