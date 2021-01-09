package com.thenewsapp.thedailynewscast.activities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thenewsapp.thedailynewscast.activities.UImodel.NewsUIModel
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel
import com.thenewsapp.thedailynewscast.activities.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPoliticsViewModel() : ViewModel(), Callback<NewsResponseModel> {
    private val newsRepository = NewsRepository(this)
    private val mutableLiveData = MutableLiveData<NewsUIModel>()
    val liveDataForNews: LiveData<NewsUIModel> = mutableLiveData

    override fun onFailure(call: Call<NewsResponseModel>, t: Throwable) {
        mutableLiveData.value = NewsUIModel.Failure(t.message!!)

    }

    override fun onResponse(
        call: Call<NewsResponseModel>,
        response: Response<NewsResponseModel>
    ) {
        response.body()?.let {
            mutableLiveData.value = NewsUIModel.Success(it.data as List<DataItem>)
        }
    }

    fun callApi() {
        newsRepository.getListOfNews()
    }
}





