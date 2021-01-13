package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.TechnologyResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface TechnologyApiClient {

    @GET("/news?category=technology")
    fun getTechnologyData() : Call<TechnologyResponseModel>
}