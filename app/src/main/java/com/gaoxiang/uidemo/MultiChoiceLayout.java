package com.gaoxiang.uidemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public class MultiChoiceLayout extends RelativeLayout implements Checkable {
    protected CheckBox mCheckView = null;

    public MultiChoiceLayout(Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mCheckView = (CheckBox) findViewById(R.id.listview_scrollchoice_checkbox);
    }

    @Override
    public boolean isChecked() {
        return mCheckView.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        mCheckView.setChecked(checked);
    }

    @Override
    public void toggle() {
        mCheckView.toggle();
    }
}
