package com.thenewsapp.thedailynewscast.activities.listener

import com.thenewsapp.thedailynewscast.activities.models.DataItem


interface UrlFragmentListener {
    fun onFragmentDataPassed(dataItem: DataItem)
}