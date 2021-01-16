package com.thenewsapp.thedailynewscast.activities.Network

import com.thenewsapp.thedailynewscast.activities.models.TravelResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface TravelApiClient {
    @GET("/news?category=travel")
    fun getTravelNewsData(): Call<TravelResponseModel>
}