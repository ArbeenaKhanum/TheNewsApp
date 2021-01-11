package com.thenewsapp.activities.models

import com.google.gson.annotations.SerializedName
import com.thenewsapp.thedailynewscast.activities.models.DataItem

data class BusinessResponseModel(

    @field:SerializedName("category")
	val category: String? = null,

    @field:SerializedName("data")
	val data: List<DataItem?>? = null,

    @field:SerializedName("success")
	val success: Boolean? = null
)