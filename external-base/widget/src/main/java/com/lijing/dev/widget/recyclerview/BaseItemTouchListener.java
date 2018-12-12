package com.lijing.dev.widget.recyclerview;

import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.lijing.dev.widget.recyclerview.adapter.BaseCommonAdapter;

public class BaseItemTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView mCurrentView;
    private GestureDetectorCompat mGestureDetector;
    private BaseCommonAdapter mCommonAdapter;

    /**
     * 当前点击的 View
     */
    private View mPressedView;
    private boolean mIsPressed;


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (mCurrentView == null || !mCurrentView.equals(recyclerView)) {
            mCurrentView = recyclerView;
            mCommonAdapter = (BaseCommonAdapter) recyclerView.getAdapter();
            mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new CustomGestureDetector());
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {
    }

    class CustomGestureDetector implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            mIsPressed = true;
            mPressedView = mCurrentView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    }
}
