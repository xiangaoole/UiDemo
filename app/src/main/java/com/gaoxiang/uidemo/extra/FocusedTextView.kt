package com.gaoxiang.uidemo.extra

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class FocusedTextView : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun isFocused() = true
}