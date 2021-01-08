package com.thenewsapp.thedailynewscast.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.google.firebase.database.*
import com.mohitdev.verticalviewpager.VerticalViewPager
import com.thenewsapp.thedailynewscast.Network.ApiClient
import com.thenewsapp.thedailynewscast.Network.Network
import com.thenewsapp.thedailynewscast.R
import com.thenewsapp.thedailynewscast.SliderContents
import com.thenewsapp.thedailynewscast.VerticalSwipeViewPager
import com.thenewsapp.thedailynewscast.ViewPagerAdapter
import com.thenewsapp.thedailynewscast.adapter.FragmentsAdapter
import com.thenewsapp.thedailynewscast.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.fragments.NewsDetailsFragment
import com.thenewsapp.thedailynewscast.fragments.ReadMoreFragment
import com.thenewsapp.thedailynewscast.fragments.UrlViewFragment
import com.thenewsapp.thedailynewscast.listener.ReadMoreNewsListener
import com.thenewsapp.thedailynewscast.listener.UrlFragmentListener
import com.thenewsapp.thedailynewscast.models.DataItem
import com.thenewsapp.thedailynewscast.models.NewsResponseModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news_content_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class MainActivity : AppCompatActivity(), UrlFragmentListener, ReadMoreNewsListener {
    private lateinit var fragmentsAdapter: FragmentsAdapter
    private lateinit var verticalViewPager: VerticalSwipeViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private lateinit var newsListAdapter: NewsListAdapter
    private val dataItemList = emptyList<DataItem>()
    val dataItem: DataItem? = null

    private val sliderContents = mutableListOf<SliderContents>()
    private val title = ArrayList<String>()
    private val content = ArrayList<String>()
    private val imageUrl = ArrayList<String>()
    private val readMoreUrl = ArrayList<String>()
    private val url = ArrayList<String>()

    private var dbReference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        launchNewsDetailsFragment()


//        CategoryBtn.setOnClickListener {
//            openCategoryActivity()
//        }
//        callApiViewPagerData()
//        setVerticalViewPager()

        val mainVerticalViewPager = findViewById<VerticalViewPager>(R.id.mainVerticalViewPager)

        dbReference = FirebaseDatabase.getInstance().getReference("data")
        dbReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnaps: DataSnapshot in snapshot.children) {
                    title.add(dataSnaps.child("title").getValue(String::class.java)!!)
                    content.add(dataSnaps.child("content").getValue(String::class.java)!!)
                    imageUrl.add(dataSnaps.child("imageUrl").getValue(String::class.java)!!)
                    readMoreUrl.add(dataSnaps.child("readMoreUrl").getValue(String::class.java)!!)
                    url.add(dataSnaps.child("url").getValue(String::class.java)!!)
                }

                for (i in 0 until imageUrl.size) {
                    sliderContents.add(SliderContents(imageUrl[i]))
                }

                mainVerticalViewPager.adapter = ViewPagerAdapter(this@MainActivity, sliderContents,
                    title, content, imageUrl, readMoreUrl, url, mainVerticalViewPager)
            }

        })

//        dataItemList.add(DataItem("author", "content", "date", "imageUrl",
//            "readMoreUrl", "time", "title", "url"))
    }

//    private fun setVerticalViewPager() {
//        mainVerticalViewPager.adapter = ViewPagerAdapter(this, sliderContents)
//    }
//
//    private fun callApiViewPagerData() {
//        val apiClient = Network.getInstance().create(ApiClient::class.java)
//        val call = apiClient.getNewsData()
//        call.enqueue(object : Callback<NewsResponseModel>{
//            override fun onFailure(call: Call<NewsResponseModel>, t: Throwable) {
//            }
//
//            override fun onResponse(
//                call: Call<NewsResponseModel>,
//                response: Response<NewsResponseModel>
//            ) {
//
//               response.body().let {
//                   if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
//                       for (i in 0 until it?.data?.size!!)
//                          sliderContents.add(SliderContents(it.data[i]?.content!!, it.data[i]?.title!!))
//                   }
//               }
//            }
//
//        })
//    }

    private fun setViewPagerAdapter() {
        fragmentsAdapter = FragmentsAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
    }

    private fun setViewPager() {

    }

    private fun openCategoryActivity() {
        val openCategoryIntent = Intent(this, CategoryActivity::class.java)
        startActivity(openCategoryIntent)
    }

    private fun launchNewsDetailsFragment() {
        val newsDetailsFragment = NewsDetailsFragment()
//        supportFragmentManager.beginTransaction().add(R.id.flContainer, newsDetailsFragment, "NewsDetailsFragment").commit()
    }

    override fun onFragmentDataPassed(dataItem: DataItem) {
        val urlViewFragment = UrlViewFragment()
        val bundle = Bundle()
        bundle.putString("urlLinkText", dataItem.url)
        urlViewFragment.arguments = bundle
//        supportFragmentManager.beginTransaction().replace(R.id.flContainer, urlViewFragment, "UrlViewFragment")
//            .addToBackStack("urlViewFragment").commit()
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
//        supportFragmentManager.beginTransaction().replace(R.id.flContainer, readMoreFragment, "ReadMoreFragment")
//            .addToBackStack("readMoreFragment").commit()
    }
}