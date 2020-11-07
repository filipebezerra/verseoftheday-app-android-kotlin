package com.github.filipebezerra.bible.verseoftheday.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("goneUnless")
fun bindGoneUnless(view: View, notGone: Boolean) {
    view.visibility = if (notGone) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleUnless")
fun bindVisibleUnless(view: View, notVisible: Boolean) {
    view.visibility = if (notVisible) View.GONE else View.VISIBLE
}