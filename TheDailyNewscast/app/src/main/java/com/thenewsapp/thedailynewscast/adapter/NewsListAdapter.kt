package com.thenewsapp.thedailynewscast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewsapp.thedailynewscast.R
import com.thenewsapp.thedailynewscast.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.models.DataItem
import com.thenewsapp.thedailynewscast.viewholder.NewsListViewHolder

class NewsListAdapter (private var dataItemList:  List<DataItem>, private val recyclerviewClickListener: RecyclerviewClickListener)
    : RecyclerView.Adapter<NewsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_content_layout, parent, false)
        return NewsListViewHolder(view, recyclerviewClickListener)
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val newsDataItem = dataItemList[position]
        holder.setData(newsDataItem)
    }

    fun updateNewsList(dataItemList: List<DataItem>) {
        this.dataItemList = dataItemList
        notifyDataSetChanged()
    }
}