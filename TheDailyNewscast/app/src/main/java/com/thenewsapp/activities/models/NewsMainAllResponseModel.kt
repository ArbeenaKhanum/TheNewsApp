package com.thenewsapp.thedailynewscast.activities.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsMainAllResponseModel(

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)