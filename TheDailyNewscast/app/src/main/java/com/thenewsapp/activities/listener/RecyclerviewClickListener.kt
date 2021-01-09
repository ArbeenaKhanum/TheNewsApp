package com.thenewsapp.thedailynewscast.activities.listener

import com.thenewsapp.thedailynewscast.activities.models.DataItem

interface RecyclerviewClickListener {
//    fun onUrlClicked(position :  Int, dataItem: DataItem)
    fun onReadMoreClicked(position: Int, dataItem: DataItem)
}