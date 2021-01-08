package com.thenewsapp.thedailynewscast.repository

import com.thenewsapp.thedailynewscast.Network.Network
import com.thenewsapp.thedailynewscast.Network.SearchApiClient
import com.thenewsapp.thedailynewscast.models.NewsResponseModel
import retrofit2.Callback

class NewsSearchRepository(private val callback : Callback<NewsResponseModel>) {
    fun getListOfSearches(category : String) {
        val searchApiClient = Network.getInstance().create(SearchApiClient::class.java)
        val callSearch = searchApiClient.getListOfSearch(category)
        callSearch.enqueue(callback)
    }
}