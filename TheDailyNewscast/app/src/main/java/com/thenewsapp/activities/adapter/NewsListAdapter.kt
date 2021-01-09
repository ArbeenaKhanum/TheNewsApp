package com.thenewsapp.thedailynewscast.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.viewholder.NewsListViewHolder

class NewsListAdapter(
    private var dataItemList: List<DataItem>,
    private val recyclerviewClickListener: RecyclerviewClickListener
) : RecyclerView.Adapter<NewsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_politics_layout, parent, false)
        return NewsListViewHolder(view, recyclerviewClickListener)
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    fun updateNewsList(dataItemList: List<DataItem>) {
        this.dataItemList = dataItemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val newsDataItem = dataItemList[position]
        holder.setData(newsDataItem)
    }
}