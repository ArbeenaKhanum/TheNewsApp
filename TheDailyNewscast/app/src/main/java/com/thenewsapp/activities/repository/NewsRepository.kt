package com.thenewsapp.thedailynewscast.activities.repository

import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.Network.PoliticsApiClient
import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel
import retrofit2.Callback

class NewsRepository(private val callback: Callback<NewsResponseModel>) {
    fun getListOfNews() {
        val apiClient = Network.getInstance().create(PoliticsApiClient::class.java)
        val call = apiClient.getNewsData()
        call.enqueue(callback)
    }
}