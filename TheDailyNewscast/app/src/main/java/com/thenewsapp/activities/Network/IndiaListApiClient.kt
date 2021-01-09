package com.thenewsapp.thedailynewscast.activities.Network

import com.thenewsapp.thedailynewscast.activities.models.IndiaResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface IndiaListApiClient {
    @GET("/news?category=india")
    fun getIndiaNewsData() : Call<IndiaResponseModel>
}