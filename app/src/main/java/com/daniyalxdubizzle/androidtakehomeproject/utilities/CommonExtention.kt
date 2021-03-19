package com.daniyalxdubizzle.androidtakehomeproject.utilities

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

val String.capitalizeWords get() = this.toLowerCase().split(" ").joinToString(" ") { it.capitalize() }

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

