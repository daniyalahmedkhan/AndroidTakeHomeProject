package com.daniyalxdubizzle.androidtakehomeproject.utilities

import org.json.JSONObject

class GeneralHelper{

    companion object{

        fun parseFailureJson(jsonObject: JSONObject): String {
            val message = jsonObject.getString("message")
            val errors = jsonObject.getString("success")
            return message.trim()
        }

    }
}