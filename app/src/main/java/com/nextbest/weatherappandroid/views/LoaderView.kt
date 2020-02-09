package com.nextbest.weatherappandroid.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.nextbest.weatherappandroid.R

class LoaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.loader_view, this)
    }
}