package com.nextbest.weatherappandroid.utils

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView

fun TextView.setTextWithSpan(text: String, spanText: String, style: StyleSpan) {
    val spannableStringBuilder = SpannableStringBuilder(text)
    val start = text.indexOf(spanText)
    val end = start + spanText.length
    spannableStringBuilder.setSpan(style, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    this.text = spannableStringBuilder
}