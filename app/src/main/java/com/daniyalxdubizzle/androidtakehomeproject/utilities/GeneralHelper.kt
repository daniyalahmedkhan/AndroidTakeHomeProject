package com.daniyalxdubizzle.androidtakehomeproject.utilities

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.daniyalxdubizzle.androidtakehomeproject.R
import org.json.JSONObject

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
    }
}