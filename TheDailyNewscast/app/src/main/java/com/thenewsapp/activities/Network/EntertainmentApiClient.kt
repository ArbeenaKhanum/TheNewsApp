package com.thenewsapp.thedailynewscast.activities.Network

import com.thenewsapp.thedailynewscast.activities.models.EntertainmentResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface EntertainmentApiClient {
    @GET("/news?category=entertainment")
    fun getEntertainmentData() : Call<EntertainmentResponseModel>
}