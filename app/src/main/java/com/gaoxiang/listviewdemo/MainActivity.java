package com.gaoxiang.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import gov.nist.javax.sip.header.To;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MultiChoiceListView mListView;
    private ContactsAdapter mAdapter;
    private List<ContactsAdapter.Contacts> mList = new ArrayList<>();
    private View mFooterView;
    private TextView mFooterTextView;

    private int mTouchState = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = LayoutInflater.from(this);

        mListView = findViewById(R.id.list);
        mListView.setDivider(null);
        mListView.setVerticalFadingEdgeEnabled(false);
        mListView.setOnItemClickListener(this);

        mAdapter = new ContactsAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        initData();

        mFooterView = inflater.inflate(R.layout.custom_list_footer, null);
        mFooterTextView = (TextView) mFooterView
                .findViewById(R.id.footer_title);
        mListView.addFooterView(mFooterView, null, false);

        mListView.setMultiChoiceListener(new MultiChoiceListView.MultiChoiceListener() {
            @Override
            public void onInterceptTouch() {
                mTouchState = -1;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "You clicked " + position + "th item.", Toast.LENGTH_SHORT)
                .show();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            ContactsAdapter.Contacts contacts = new ContactsAdapter.Contacts();
            contacts.setName("测试" + i);
            contacts.setNumber(String.format("159274568%02d", i));
            mList.add(contacts);
        }
        mAdapter.notifyDataSetChanged();
    }
}
