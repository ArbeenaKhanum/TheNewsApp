package com.example.thedailynewscast.repository

import com.example.thedailynewscast.Network.ApiClient
import com.example.thedailynewscast.Network.Network
import com.example.thedailynewscast.models.NewsResponseModel
import retrofit2.Callback

class NewsRepository(private val callback: Callback<NewsResponseModel>) {
    fun getListOfNews() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        val call = apiClient.getNewsData()
        call.enqueue(callback)
    }
}