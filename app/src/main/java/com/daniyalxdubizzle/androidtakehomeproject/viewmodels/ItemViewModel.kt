package com.daniyalxdubizzle.androidtakehomeproject.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniyalxdubizzle.androidtakehomeproject.data.model.remote.ResponseEvent
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemListResponse
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemResponse
import com.daniyalxdubizzle.androidtakehomeproject.data.repo.ListingsItemRepo
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class ItemViewModel @ViewModelInject constructor(private val listingsItemRepo: ListingsItemRepo) :
    ViewModel() {

    val itemState: MutableLiveData<ResponseEvent<ItemResponse>> = MutableLiveData()
    val itemPos: MutableLiveData<ItemListResponse> = MutableLiveData()

    init {
        searchItems()
    }

    /*
    * To make invoke repository and handle api 3 events Success, Failure, Exception
    * */
    private fun searchItems() {
        itemState.value = ResponseEvent.Loading
        viewModelScope.launch {
            Dispatchers.IO
            try {
                val response = listingsItemRepo.fetchItemsFromServer()
                if (response.isSuccessful) {
                    itemState.value = ResponseEvent.Success(response.body())
                } else {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    val message = GeneralHelper.parseFailureJson((jObjError))
                    itemState.value = ResponseEvent.Failure(message)
                }

            } catch (e: Exception) {
                itemState.value = ResponseEvent.Failure(e.message)

            }
        }

    }

    /*
    * Setting ItemPos from list click event which has to view in detail screen
    * */
    fun setItemPos(item: ItemListResponse){
        itemPos.value = item
    }

    /*
    * View Item info on detail screen by fetching from this method
    * */
    fun getItemPosValue() : LiveData<ItemListResponse>{
        return itemPos
    }



}