package com.thenewsapp.thedailynewscast.activities.repository

import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.Network.SearchApiClient
import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel
import retrofit2.Callback

class NewsSearchRepository(private val callback : Callback<NewsResponseModel>) {
    fun getListOfSearches(category : String) {
        val searchApiClient = Network.getInstance().create(SearchApiClient::class.java)
        val callSearch = searchApiClient.getListOfSearch(category)
        callSearch.enqueue(callback)
    }
}