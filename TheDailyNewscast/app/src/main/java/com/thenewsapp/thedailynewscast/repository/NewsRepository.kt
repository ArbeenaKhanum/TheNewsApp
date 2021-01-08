package com.thenewsapp.thedailynewscast.repository

import com.thenewsapp.thedailynewscast.Network.ApiClient
import com.thenewsapp.thedailynewscast.Network.Network
import com.thenewsapp.thedailynewscast.models.NewsResponseModel
import retrofit2.Callback

class NewsRepository(private val callback: Callback<NewsResponseModel>) {
    fun getListOfNews() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        val call = apiClient.getNewsData()
        call.enqueue(callback)
    }
}