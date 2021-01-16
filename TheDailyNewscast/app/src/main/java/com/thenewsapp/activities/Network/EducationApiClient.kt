package com.thenewsapp.activities.Network

import com.thenewsapp.activities.models.AutomobileResponseModel
import com.thenewsapp.activities.models.EducationResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface EducationApiClient {
    @GET("/news?category=education")
    fun getEducationNewsData() : Call<EducationResponseModel>
}