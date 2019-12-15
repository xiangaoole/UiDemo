package com.gaoxiang.uidemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.gaoxiang.uidemo.R
import com.gaoxiang.uidemo.extra.ChildBaseActivity
import kotlinx.android.synthetic.main.activity_text_view.*

class TextViewActivity : ChildBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)

        verticalScrollText.movementMethod = ScrollingMovementMethod.getInstance()
    }
}
