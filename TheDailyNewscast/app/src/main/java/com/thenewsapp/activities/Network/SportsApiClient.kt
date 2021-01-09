package com.thenewsapp.thedailynewscast.activities.Network

import com.thenewsapp.thedailynewscast.activities.models.SportsResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface SportsApiClient {
    @GET("news?category=sports")
    fun getSportsData() : Call<SportsResponseModel>
}