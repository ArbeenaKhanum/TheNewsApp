package com.thenewsapp.thedailynewscast.activities.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.news_search_list_layout.view.*

class NewsSearchListViewHolder(private val view : View, private val recyclerviewClickListener: RecyclerviewClickListener) : RecyclerView.ViewHolder(view) {
    fun setData(newsSearchDataItem: DataItem) {
        view.apply {
            tvSearchTitle.text = newsSearchDataItem.title
            Glide.with(ivSearchImg).load(newsSearchDataItem.imageUrl).into(ivSearchImg)

            cvSearchNews.setOnClickListener {
                recyclerviewClickListener.onReadMoreClicked(adapterPosition, newsSearchDataItem)
            }
        }
    }
}