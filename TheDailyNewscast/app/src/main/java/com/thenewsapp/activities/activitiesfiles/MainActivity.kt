package com.thenewsapp.thedailynewscast.activities.activitiesfiles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.mohitdev.verticalviewpager.VerticalViewPager
import com.thenewsapp.thedailynewscast.activities.SliderContents
import com.thenewsapp.thedailynewscast.activities.adapter.NewsSearchListAdapter
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.verticalviewpager.VerticalSwipeViewPager
import com.thenewsapp.thedailynewscast.activities.verticalviewpager.ViewPagerAdapter
import com.thenewsapp.thedailynewscast.activities.R


class MainActivity : AppCompatActivity() {
    private lateinit var newsSearchListAdapter: NewsSearchListAdapter
    private lateinit var verticalViewPager: VerticalSwipeViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter

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

                mainVerticalViewPager.adapter =
                    ViewPagerAdapter(
                        this@MainActivity, sliderContents,
                        title, content, imageUrl, readMoreUrl, url, mainVerticalViewPager
                    )
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
 //       newsSearchListAdapter = NewsSearchListAdapter(
//            supportFragmentManager,
//            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
//        )
    }

    private fun setViewPager() {

    }

    private fun openCategoryActivity() {
        val openCategoryIntent = Intent(this, CategoryActivity::class.java)
        startActivity(openCategoryIntent)
    }




//        val urlViewFragment = PoliticsViewFragment()
//        val bundle = Bundle()
//        bundle.putString("urlLinkText", dataItem.url)
//        urlViewFragment.arguments = bundle
////        supportFragmentManager.beginTransaction().replace(R.id.flContainer, urlViewFragment, "UrlViewFragment")
////            .addToBackStack("urlViewFragment").commit()


}