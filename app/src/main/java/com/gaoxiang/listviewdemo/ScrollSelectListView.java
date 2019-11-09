package com.gaoxiang.listviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

public class ScrollSelectListView extends ListView {

    private static final int INVALID_SCROLL_CHOICE_POSITION = -2;
    private static final long SCROLL_CHOICE_SCROLL_DELAY = 50L;
    
    private ScrollSelectListView.ScrollMultiChoiceListener mScrollMultiChoiceListener;
    private boolean mMultiChoice;
    private int mLastPosition;
    private int mLasterPosition;
    private boolean mFlag;
    private CheckBox mCheckBox;
    private boolean mUpScroll;
    private int mLastSite;
    private int mCheckItemId;
    private Runnable mDelayedScroll;

    public ScrollSelectListView(Context context) {
        this(context, null);
    }

    public ScrollSelectListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMultiChoice = true;
        mLastPosition = INVALID_SCROLL_CHOICE_POSITION;
        mLasterPosition = INVALID_SCROLL_CHOICE_POSITION;
        mFlag = false;
        mCheckBox = null;
        mUpScroll = true;
        mLastSite = -1;
        mCheckItemId = -1;
        mDelayedScroll = new Runnable() {
            public void run() {
                if (mUpScroll) {
                    setSelection(getFirstVisiblePosition() - 1);
                } else {
                    alignBottomChild(getLastVisiblePosition() + 1);
                }

            }
        };
    }

    private void alignBottomChild(int position) {
        alignBottomChild(position, 0);
    }

    private void alignBottomChild(int position, int offset) {
        int positionTop = getHeight() - getPaddingTop();
        int childHeight = getChildAt(getChildCount() - 1).getHeight();
        setSelectionFromTop(position, positionTop - childHeight + offset);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int action;
        if (mMultiChoice && isInScrollRange(ev)) {
            action = (int)ev.getX();
            int n = (int)ev.getY();
            int curPosition = pointToPosition(action, n);
            switch(ev.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    mFlag = true;
                case MotionEvent.ACTION_MOVE:
                    if (curPosition == getCount() - 1) {
                        alignBottomChild(curPosition);
                    }

                    if (mFlag && mLastPosition != curPosition && curPosition != -1 && null != mScrollMultiChoiceListener) {
                        removeCallbacks(mDelayedScroll);
                        if (mLastSite != -1 && mLastSite != curPosition - 1 && mLastSite < curPosition) {
                            mScrollMultiChoiceListener.onItemTouch(mLastSite + 1, getChildAt(mLastSite + 1 - getFirstVisiblePosition()));
                        }

                        mScrollMultiChoiceListener.onItemTouch(curPosition, getChildAt(curPosition - getFirstVisiblePosition()));
                        mLastSite = curPosition;
                        if (mLastPosition != INVALID_SCROLL_CHOICE_POSITION) {
                            if (curPosition == getFirstVisiblePosition() && curPosition > 0) {
                                mUpScroll = true;
                                postDelayed(mDelayedScroll, SCROLL_CHOICE_SCROLL_DELAY);
                            } else if (curPosition == getLastVisiblePosition() && curPosition < getCount()) {
                                mUpScroll = false;
                                postDelayed(mDelayedScroll, SCROLL_CHOICE_SCROLL_DELAY);
                            }
                        }

                        if (mLasterPosition == curPosition) {
                            mScrollMultiChoiceListener.onItemTouch(mLastPosition, getChildAt(mLastPosition - getFirstVisiblePosition()));
                        }

                        mLasterPosition = mLastPosition;
                        mLastPosition = curPosition;
                    }

                    return true;
                case MotionEvent.ACTION_UP:
                    mLastPosition = INVALID_SCROLL_CHOICE_POSITION;
                    mLasterPosition = INVALID_SCROLL_CHOICE_POSITION;
            }
        }

        action = ev.getAction();
        switch(action & 255) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mUpScroll = true;
                mLastPosition = INVALID_SCROLL_CHOICE_POSITION;
                mLasterPosition = INVALID_SCROLL_CHOICE_POSITION;
                mFlag = false;
                mMultiChoice = true;
                mLastSite = -1;
            default:
                return super.onTouchEvent(ev);
        }
    }

    private boolean isInScrollRange(MotionEvent ev) {
        int m = (int)ev.getX();
        int n = (int)ev.getY();
        int curPosition = pointToPosition(m, n);
        int xRaw = (int)ev.getRawX();
        int[] location = new int[2];

        try {
            if (mCheckItemId <= 0) {
                mMultiChoice = false;
                return false;
            }

            mCheckBox = getChildAt(curPosition - getFirstVisiblePosition()).findViewById(mCheckItemId);
            mCheckBox.getLocationOnScreen(location);
        } catch (Exception var9) {
            if (ev.getActionMasked() == 0) {
                mMultiChoice = false;
            }

            return false;
        }

        int mLeftBorder = location[0];
        int mRightBorder = location[0];
        if (View.VISIBLE == mCheckBox.getVisibility()
                && xRaw > mLeftBorder && xRaw < mRightBorder
                && curPosition > getHeaderViewsCount() - 1
                && curPosition < getCount() - getFooterViewsCount()) {
            return true;
        } else {
            if (ev.getActionMasked() == 0) {
                mMultiChoice = false;
            }

            return false;
        }
    }

    public void setCheckItemId(int id) {
        mCheckItemId = id;
    }

    public interface ScrollMultiChoiceListener {
        void onItemTouch(int var1, View var2);
    }
}
