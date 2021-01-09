package com.thenewsapp.thedailynewscast.activities.activitiesfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.fragments.NewsDetailsFragment
import com.thenewsapp.thedailynewscast.activities.fragments.ReadMoreFragment
import com.thenewsapp.thedailynewscast.activities.listener.ReadMoreNewsListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem

class SearchActivity : AppCompatActivity(), ReadMoreNewsListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        launchNewsDetailsFragment()
    }

    private fun launchNewsDetailsFragment() {
        val newsDetailsFragment = NewsDetailsFragment()
        supportFragmentManager.beginTransaction().add(R.id.flContainer, newsDetailsFragment, "NewsDetailsFragment").commit()
    }

    override fun onFragmentReadMorePassed(dataItem: DataItem) {
            val readMoreFragment = ReadMoreFragment()
            val bundleRead = Bundle()
            bundleRead.putString("readMoreImg", dataItem.imageUrl)
            bundleRead.putString("readMoreHeadline", dataItem.title)
            bundleRead.putString("readMoreDesc", dataItem.content)
            bundleRead.putString("readMoreTime", dataItem.time)
            bundleRead.putString("readMoreDate", dataItem.date)
            bundleRead.putString("readMoreAuthor", dataItem.author)

            readMoreFragment.arguments = bundleRead
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, readMoreFragment, "ReadMoreFragment")
            .addToBackStack("readMoreFragment").commit()
    }
}