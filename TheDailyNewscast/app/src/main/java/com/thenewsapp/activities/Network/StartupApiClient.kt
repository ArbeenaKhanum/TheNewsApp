package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.StartupResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface StartupApiClient {
    @GET("/news?category=startup")
    fun getStartupNewsData(): Call<StartupResponseModel>
}