package com.thenewsapp.activities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataItem(

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("readMoreUrl")
	val readMoreUrl: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)