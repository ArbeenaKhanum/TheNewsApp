package com.example.thedailynewscast.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thedailynewscast.UImodel.NewsUIModel
import com.example.thedailynewscast.models.DataItem
import com.example.thedailynewscast.models.NewsResponseModel
import com.example.thedailynewscast.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel() : ViewModel(), Callback<NewsResponseModel> {
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





