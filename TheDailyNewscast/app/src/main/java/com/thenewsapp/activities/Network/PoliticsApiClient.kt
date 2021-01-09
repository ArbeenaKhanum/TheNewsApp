package com.thenewsapp.thedailynewscast.activities.Network

import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface PoliticsApiClient {
    @GET("/news?category=politics")
    fun getNewsData(): Call<NewsResponseModel>
}