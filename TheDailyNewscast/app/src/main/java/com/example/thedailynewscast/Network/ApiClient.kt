package com.example.thedailynewscast.Network

import com.example.thedailynewscast.models.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("/news?category=politics")
    fun getNewsData(): Call<NewsResponseModel>
}