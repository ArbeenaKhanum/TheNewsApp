package com.example.thedailynewscast.UImodel

import com.example.thedailynewscast.models.DataItem

sealed class NewsUIModel {
    data class Success(val dataItemList : List<DataItem>) : NewsUIModel()
    data class Failure(val error : String) : NewsUIModel()
}