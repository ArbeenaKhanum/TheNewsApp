package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.HealthResponseModel
import com.thenewsapp.activities.models.MiscellaneousResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface MiscellaneousApiClient {
    @GET("/news?category=miscellaneous")
    fun getMiscellaneousNewsData() : Call<MiscellaneousResponseModel>
}