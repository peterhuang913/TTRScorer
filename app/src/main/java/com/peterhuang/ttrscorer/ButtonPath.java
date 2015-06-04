package com.peterhuang.ttrscorer;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by peterhuang on 4/21/15.
 */
public class ButtonPath {

    private final Button[] buttons;
    private final MainActivity mainActivity;
    private final FrameImageLayout frame;
    private int state = -1;
    private final int[] colors = new int[] {Color.GREEN, Color.RED, Color.YELLOW, Color.BLUE, Color.BLACK};
    private final int[] points = new int[] {0, 1, 2, 4, 7, 10, 15};

    public ButtonPath (int[] buttons, MainActivity mainActivity) {
        this.buttons = new Button[buttons.length];
        this.mainActivity = mainActivity;
        this.frame = (FrameImageLayout) mainActivity.findViewById(R.id.view);
        View.OnTouchListener listener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchEvent(event);
            }
        };
        int idx = 0;
        for (int i : buttons) {
            this.buttons[idx] = (Button) mainActivity.findViewById(i);
            this.buttons[idx].setOnTouchListener(listener);
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);
            Drawable d = this.buttons[idx++].getBackground();
            d.setColorFilter(filter);
        }
    }

    boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            frame.setButtonPressed(true);
        } else if (event.getAction() == MotionEvent.ACTION_UP && frame.getButtonPressed()) {
            if (mainActivity.getSelected() > -1) {
                updateState(mainActivity.getSelected());
            } else {
                updateState(-1);
            }
            frame.setButtonPressed(false);
        }
        return true;
    }

    public void updateState(int newState) {
        if (state > -1) {
            mainActivity.changeScore(state, -points[buttons.length]);
        }
        state = newState;
        if (state > -1) {
            mainActivity.changeScore(state, points[buttons.length]);
            PorterDuffColorFilter filter = new PorterDuffColorFilter(colors[state], PorterDuff.Mode.SRC_ATOP);
            for (Button b : buttons) {
                Drawable d = b.getBackground();
                d.setColorFilter(filter);
            }
        } else {
            PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);
            for (Button b : buttons) {
                Drawable d = b.getBackground();
                d.setColorFilter(filter);
            }
        }
    }

}
