package com.daniyalxdubizzle.androidtakehomeproject.data.repo

import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemResponse
import com.daniyalxdubizzle.androidtakehomeproject.data.retrofit.RetrofitInterface
import retrofit2.Response
import javax.inject.Inject

class ListingsItemRepo @Inject constructor(private val baseApiInterface: RetrofitInterface){

    suspend fun fetchItemsFromServer() : Response<ItemResponse>{
        return  baseApiInterface.getListItems()
    }
}