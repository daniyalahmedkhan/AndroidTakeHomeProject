package com.daniyalxdubizzle.androidtakehomeproject.data.retrofit

import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("dynamodb-writer")
    suspend fun getListItems() : Response<ItemResponse>
    
}