package com.daniyalxdubizzle.androidtakehomeproject.utilities

import android.app.Activity
import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.daniyalxdubizzle.androidtakehomeproject.R
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class GeneralHelper {

    companion object {

        fun parseFailureJson(jsonObject: JSONObject): String {
            val message = jsonObject.getString("message")
            val errors = jsonObject.getString("success")
            return message.trim()
        }


        fun loadImage(context: Context, url: String, view: ImageView) {
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(view)
        }

        fun dateParse(date: String): String? {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
            try {
                val time: Long = sdf.parse(date).getTime()
                val now = System.currentTimeMillis()
                return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                    .toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return null
        }


        fun isDateParse(date: String): Boolean {
            try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"))

                val time: Long = sdf.parse(date).getTime()
                val now = System.currentTimeMillis()
                return true
            } catch (e: ParseException) {
                return false
            }
            return false
        }

        fun hideKeyboardFrom(context: Context, view: View) {
            val imm: InputMethodManager =
                context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }

    }
}