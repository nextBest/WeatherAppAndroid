package com.nextbest.weatherappandroid.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.nextbest.weatherappandroid.R
import kotlinx.android.synthetic.main.error_view.view.*

class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var listener: Listener? = null

    init {
        initView()
        setupView()
    }

    private fun initView() {
        View.inflate(context, R.layout.error_view, this)
    }

    private fun setupView() {
        reloadBtn.setOnClickListener {
            listener?.reload()
        }
    }

    private fun setConnectionError() {
        title.text = context.getString(R.string.error_view_connection_error_title)
        message.text = context.getString(R.string.error_view_connection_error_message)
    }

    private fun setUnknownError() {
        title.text = context.getString(R.string.error_view_unknown_error_title)
        message.text = context.getString(R.string.error_view_unknown_error_message)
    }

    fun setErrorType(errorType: ErrorType) {
        when (errorType) {
            ErrorType.CONNECTION_ERROR -> setConnectionError()
            ErrorType.UNKNOWN_ERROR -> setUnknownError()
        }
    }

    fun setReloadListener(listener: Listener) {
        this.listener = listener
    }

    enum class ErrorType {
        CONNECTION_ERROR, UNKNOWN_ERROR
    }

    interface Listener {
        fun reload()
    }
}