package com.thenewsapp.thedailynewscast.activities.listener
import com.thenewsapp.thedailynewscast.activities.models.DataItem

interface ReadMoreNewsListener {
    fun onFragmentReadMorePassed(dataItem: DataItem)
}