package com.thenewsapp.thedailynewscast.activities.Network

import com.thenewsapp.thedailynewscast.activities.models.EntertainmentResponseModel
import com.thenewsapp.thedailynewscast.activities.models.FashionResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface FashionApiClient {
    @GET("/news?category=fashion")
    fun getFashionNewsData() : Call<FashionResponseModel>
}