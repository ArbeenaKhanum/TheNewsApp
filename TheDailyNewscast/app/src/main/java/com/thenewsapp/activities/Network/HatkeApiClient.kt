package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.HatkeResponseModel
import com.thenewsapp.thedailynewscast.activities.models.FashionResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface HatkeApiClient {
    @GET("/news?category=hatke")
    fun getHatkeNewsData() : Call<HatkeResponseModel>
}