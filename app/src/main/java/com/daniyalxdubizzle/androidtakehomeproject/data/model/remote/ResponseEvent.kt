package com.daniyalxdubizzle.androidtakehomeproject.data.model.remote

sealed class ResponseEvent<out R> {
    object Loading : ResponseEvent<Nothing>()
    class Failure(val error: String?) : ResponseEvent<Nothing>()
    data class Success<out T>(val data: T?) : ResponseEvent<T>()

}