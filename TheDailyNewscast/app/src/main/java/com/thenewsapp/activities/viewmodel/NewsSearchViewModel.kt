package com.thenewsapp.thedailynewscast.activities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thenewsapp.thedailynewscast.activities.UImodel.NewsSearchUIModel
import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel
import com.thenewsapp.thedailynewscast.activities.repository.NewsSearchRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSearchViewModel : ViewModel(), Callback<NewsResponseModel>{
    private val newsSearchRepository = NewsSearchRepository(this)
    private val mutableLiveData = MutableLiveData<NewsSearchUIModel>()
    val liveDataSearchNews: LiveData<NewsSearchUIModel> = mutableLiveData

    override fun onFailure(call: Call<NewsResponseModel>, t: Throwable) {
        mutableLiveData.value = NewsSearchUIModel.Failure(t.message!!)
    }

    override fun onResponse(call: Call<NewsResponseModel>, response: Response<NewsResponseModel>) {
        response.body()?.let {
            mutableLiveData.value = NewsSearchUIModel.Success(it)
        }
    }

    fun callSearchApi(category : String) {
        newsSearchRepository.getListOfSearches(category)
    }

}