package com.thenewsapp.thedailynewscast.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.viewholder.NewsSearchListViewHolder

class NewsSearchListAdapter(private var dataItemList: List<DataItem>, private val recyclerviewClickListener: RecyclerviewClickListener) : RecyclerView.Adapter<NewsSearchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSearchListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_search_list_layout, parent, false)
        return NewsSearchListViewHolder(view, recyclerviewClickListener)
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: NewsSearchListViewHolder, position: Int) {
        val newsSearchDataItem = dataItemList[position]
        holder.setData(newsSearchDataItem)
    }

    fun updateNewsSearchList(dataItemList: List<DataItem>) {
        this.dataItemList = dataItemList
        notifyDataSetChanged()
    }
}