package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.AutomobileResponseModel
import com.thenewsapp.activities.models.InternationalResponseModel

import retrofit2.Call
import retrofit2.http.GET

interface InternationalApiClient {
    @GET("/news?category=international")
    fun getInternationalNewsData() : Call<InternationalResponseModel>
}