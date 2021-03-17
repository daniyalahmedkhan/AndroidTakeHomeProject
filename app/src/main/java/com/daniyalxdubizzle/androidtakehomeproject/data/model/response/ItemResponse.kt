package com.daniyalxdubizzle.androidtakehomeproject.data.model.response

import com.google.gson.annotations.SerializedName


data class ItemResponse (

	@SerializedName("results") val results : List<ItemListResponse>,
	@SerializedName("pagination") val itemPagination : ItemPagination
)