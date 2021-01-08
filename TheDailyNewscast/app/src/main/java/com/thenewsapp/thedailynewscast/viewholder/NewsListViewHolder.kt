package com.thenewsapp.thedailynewscast.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thenewsapp.thedailynewscast.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.models.DataItem
import kotlinx.android.synthetic.main.news_content_layout.view.*

class NewsListViewHolder(
    private val view: View,
    private val recyclerviewClickListener: RecyclerviewClickListener) : RecyclerView.ViewHolder(view) {
    fun setData(newsDataItem: DataItem) {
        view.apply {
            tvNewsHeadlines.text = newsDataItem.title
            tvNewsDesc.text = newsDataItem.content
            Glide.with(ivNewsImg).load(newsDataItem.imageUrl).into(ivNewsImg)
            tvHeading.text = newsDataItem.title

            tvHeading.setOnClickListener(View.OnClickListener {
                recyclerviewClickListener.onUrlClicked(adapterPosition, newsDataItem)
            })

            tvSwipeText.setOnClickListener(View.OnClickListener {
                recyclerviewClickListener.onReadMoreClicked(adapterPosition, newsDataItem)
            })
        }
    }
}