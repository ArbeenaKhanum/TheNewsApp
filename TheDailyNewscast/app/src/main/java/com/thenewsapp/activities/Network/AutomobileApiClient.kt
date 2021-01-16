package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.AutomobileResponseModel

import retrofit2.Call
import retrofit2.http.GET

interface AutomobileApiClient {
    @GET("/news?category=automobile")
    fun getAutomobileNewsData() : Call<AutomobileResponseModel>
}