package com.daniyalxdubizzle.androidtakehomeproject.data.model.response

import com.google.gson.annotations.SerializedName


data class ItemListResponse (

	@SerializedName("created_at") val created_at : String,
	@SerializedName("price") val price : String,
	@SerializedName("name") val name : String,
	@SerializedName("uid") val uid : String,
	@SerializedName("image_ids") val image_ids : List<String>,
	@SerializedName("image_urls") val image_urls : List<String>,
	@SerializedName("image_urls_thumbnails") val image_urls_thumbnails : List<String>
)