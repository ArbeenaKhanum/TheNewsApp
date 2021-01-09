package com.thenewsapp.thedailynewscast.activities.Network
import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiClient {
    @GET("/news")
    fun getListOfSearch(@Query("category") searchCategory: String): Call<NewsResponseModel>
}