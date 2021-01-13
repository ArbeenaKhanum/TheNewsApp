package com.thenewsapp.thedailynewscast.activities.UImodel

import com.thenewsapp.thedailynewscast.activities.models.DataItem


sealed class NewsUIModel {
    data class Success(val dataItemList : List<DataItem>) : NewsUIModel()
    data class Failure(val error : String) : NewsUIModel()
}