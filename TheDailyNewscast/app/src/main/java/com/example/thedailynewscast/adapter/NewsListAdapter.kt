package com.example.thedailynewscast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thedailynewscast.R
import com.example.thedailynewscast.models.DataItem
import com.example.thedailynewscast.viewholder.NewsListViewHolder

class NewsListAdapter (private var dataItemList:  List<DataItem>) : RecyclerView.Adapter<NewsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_content_layout, parent, false)
        return NewsListViewHolder(view)
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