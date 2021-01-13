package com.thenewsapp.activities.Network

import com.thenewsapp.thedailynewscast.activities.models.NewsMainAllResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface NewsMainAllApiClient {
    @GET("/news?category=all")
    fun getNewsAllMainData(): Call<NewsMainAllResponseModel>
}