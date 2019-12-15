package com.gaoxiang.uidemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MultiChoiceListView extends ScrollSelectListView {
    private MultiChoiceListener mMultiChoiceListener;

    public interface MultiChoiceListener {
        void onInterceptTouch();
    }

    public void setMultiChoiceListener(MultiChoiceListener l) {
        mMultiChoiceListener = l;
    }

    public MultiChoiceListView(Context context) {
        this(context, null);
    }

    public MultiChoiceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (mMultiChoiceListener != null) {
                mMultiChoiceListener.onInterceptTouch();
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}