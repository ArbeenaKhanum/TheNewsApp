package com.thenewsapp.thedailynewscast.activities.UImodel

import com.thenewsapp.thedailynewscast.activities.models.NewsResponseModel

sealed class NewsSearchUIModel {
    data class Success(val newsResponseModel: NewsResponseModel) : NewsSearchUIModel()
    data class Failure(val error : String) : NewsSearchUIModel()
}