package com.peterhuang.ttrscorer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by peterhuang on 5/20/15.
 */
public class FrameImageLayout extends FrameLayout {

    private int pointerCount = 0;
    private float mLastTouchX;
    private float mLastTouchY;
    private float layoutHeight;
    private float layoutWidth;
    private float mPosX;
    private float mPosY;
    private float mScale = 1.0f;
    private float scalePointX;
    private float scalePointY;
    private final float SCROLL_THRESHOLD = 50;
    private Context context;
    private boolean buttonPressed = false;

    public boolean getButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean pressed) {
        buttonPressed = pressed;
    }

    private ScaleGestureDetector gScaleDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            scalePointX = detector.getFocusX();
            scalePointY = detector.getFocusY();
            buttonPressed = false;
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float prevScale = mScale;
            mScale *= detector.getScaleFactor();

            RelativeLayout layout = (RelativeLayout) ((Activity) context).findViewById(R.id.test);
            layoutWidth = layout.getWidth();
            layoutHeight = layout.getHeight();

            mScale = Math.max(1.0f, Math.min(mScale, 3.0f));
            if (prevScale == mScale) {
                return true;
            }
            layout.setScaleX(mScale);
            layout.setScaleY(mScale);

            float dx = detector.getFocusX() - scalePointX;
            float dy = detector.getFocusY() - scalePointY;

            scalePointX = detector.getFocusX();
            scalePointY = detector.getFocusY();

            mPosX += (mPosX - (scalePointX - (layoutWidth / 2))) * (detector.getScaleFactor() - 1);
            mPosY += (mPosY - (scalePointY - (layoutHeight) / 2)) * (detector.getScaleFactor() - 1);

            mPosX += dx;
            mPosY += dy;

            float pixelWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 333, getResources().getDisplayMetrics());
            float diffX = Math.max(0, (pixelWidth - ((Activity) context).findViewById(R.id.view).getWidth()) / 2);
            float scaleWidth = (0.5f * layoutWidth)  * (mScale - 1) + diffX;
            float scaleHeight = (0.5f * layoutHeight)  * (mScale - 1);

            mPosX =  Math.max(-scaleWidth, Math.min(mPosX, scaleWidth));
            mPosY =  Math.max(-scaleHeight, Math.min(mPosY, scaleHeight));

            layout.setTranslationX(mPosX);
            layout.setTranslationY(mPosY);

            return true;
        }
    });

    public FrameImageLayout(Context context) {
        super(context);
        this.context = context;
    }

    public FrameImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public FrameImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        this.onTouchEvent(event);
        gScaleDetector.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int action = event.getAction();
        switch(action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                ((LockableScrollView) ((Activity) context).findViewById(R.id.scroll)).setScrollingEnabled(false);
                pointerCount = event.getPointerCount();
//                Log.i("FrameImageLayout", "action down" + pointerCount);
                mLastTouchX = event.getX(event.getActionIndex());
                mLastTouchY = event.getY(event.getActionIndex());
                break;
            }
            case MotionEvent.ACTION_UP: {
                ((LockableScrollView) ((Activity) context).findViewById(R.id.scroll)).setScrollingEnabled(true);
                pointerCount = event.getPointerCount() - 1;
//                Log.i("FrameImageLayout", "action up" + pointerCount);
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                pointerCount = event.getPointerCount();
//                Log.i("FrameImageLayout", "action pointer down" + pointerCount);
                for (int p = 0; p < pointerCount; p++) {
//                    Log.i("FrameImageLayout", "pointer" + event.getPointerId(p) + " " + event.getX(p) + " " +  event.getY(p));
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                pointerCount = event.getPointerCount() - 1;
//                Log.i("FrameImageLayout", "action pointer up" + pointerCount);
                int p;
                for (p = 0; p < pointerCount; p++) {
                    if (p == event.getActionIndex()) {
                        continue;
                    }
                    break;
                }
                mLastTouchX = event.getX(p);
                mLastTouchY = event.getY(p);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!gScaleDetector.isInProgress() && pointerCount == 1) {
//                    Log.i("FrameImageLayout", "action move");
                    float dx = event.getX(event.getActionIndex()) - mLastTouchX;
                    float dy = event.getY(event.getActionIndex()) - mLastTouchY;

                    if (buttonPressed && (Math.abs(dx) > SCROLL_THRESHOLD || Math.abs(dy) > SCROLL_THRESHOLD)) {
                        buttonPressed = false;
                    }

                    if (buttonPressed) {
                        break;
                    }

                    RelativeLayout layout = (RelativeLayout) ((Activity) context).findViewById(R.id.test);
                    layoutHeight = layout.getHeight();
                    layoutWidth = layout.getWidth();

                    mPosX += dx;
                    mPosY += dy;

                    float pixelWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 333, getResources().getDisplayMetrics());
                    float diffX = Math.max(0, (pixelWidth - ((Activity) context).findViewById(R.id.view).getWidth()) / 2);
                    float scaleWidth = (0.5f * layoutWidth)  * (mScale - 1) + diffX;
                    float scaleHeight = (0.5f * layoutHeight)  * (mScale - 1);

                    mPosX =  Math.max(-scaleWidth, Math.min(mPosX, scaleWidth));
                    mPosY =  Math.max(-scaleHeight, Math.min(mPosY, scaleHeight));

                    layout.setTranslationX(mPosX);
                    layout.setTranslationY(mPosY);

                    mLastTouchX = event.getX(event.getActionIndex());
                    mLastTouchY = event.getY(event.getActionIndex());
                }
                break;
            }
        }
        super.onTouchEvent(event);
        return true;
    }
}
