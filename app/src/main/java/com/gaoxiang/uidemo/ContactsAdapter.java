package com.gaoxiang.uidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ContactsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Contacts> mList;
    private Contacts curContacts;

    private boolean mIsChoiceMode = false;

    public void setChoiceMode(boolean isChoiceMode) {
        this.mIsChoiceMode = isChoiceMode;
    }

    public static final class Contacts {
        public void setName(String name) {
            this.name = name;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        private String name;
        private String number;

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }
    }

    private final class ContactsView {
        TextView mName;
        TextView mNumber;
        CheckBox mCheckBox;
    }

    public ContactsAdapter(@NonNull Context context, @NonNull List<Contacts> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactsView view;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.custom_list_item, parent, false);
            view = new ContactsView();
            view.mName = convertView.findViewById(R.id.name);
            view.mNumber = convertView.findViewById(R.id.number);
            view.mCheckBox = convertView.findViewById(R.id.listview_scrollchoice_checkbox);
            convertView.setTag(view);
        } else {
            view = (ContactsView) convertView.getTag();
        }

        curContacts = mList.get(position);
        view.mName.setText(curContacts.getName());
        view.mNumber.setText(curContacts.getNumber());
        if (mIsChoiceMode) {
            view.mCheckBox.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
