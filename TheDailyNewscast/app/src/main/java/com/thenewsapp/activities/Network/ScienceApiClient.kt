package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.ScienceResponseModel
import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ScienceApiClient {
    @GET("/news?category=science")
    fun getScienceNewsData(): Call<ScienceResponseModel>
}