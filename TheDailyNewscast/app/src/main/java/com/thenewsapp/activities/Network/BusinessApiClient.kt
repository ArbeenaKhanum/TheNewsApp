package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.BusinessResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface BusinessApiClient {
    @GET("/news?category=business")
    fun getNewsBusinessData() : Call<BusinessResponseModel>
}