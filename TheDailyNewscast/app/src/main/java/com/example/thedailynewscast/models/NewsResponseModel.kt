package com.example.thedailynewscast.models

import com.google.gson.annotations.SerializedName

data class NewsResponseModel(

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)