package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.HealthResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface HealthFitnessApiClient {
    @GET("/news?category=health&fitness")
    fun getHealthFitnessNewsData() : Call<HealthResponseModel>
}