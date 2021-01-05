package com.example.thedailynewscast.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thedailynewscast.models.DataItem
import kotlinx.android.synthetic.main.news_content_layout.view.*

class NewsListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(newsDataItem: DataItem) {
        view.apply {
            tvNewsHeadlines.text = newsDataItem.title
            tvNewsDesc.text = newsDataItem.content
            tvSwipeText.text = newsDataItem.url
            Glide.with(ivNewsImg).load(newsDataItem.imageUrl).into(ivNewsImg)
            tvDate.text = newsDataItem.date
            tvTime.text = newsDataItem.time
            tvHeading.text = newsDataItem.title
            tvAuthor.text = newsDataItem.author
        }
    }
}