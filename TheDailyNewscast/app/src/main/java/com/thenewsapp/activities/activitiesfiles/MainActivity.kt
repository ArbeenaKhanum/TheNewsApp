package com.thenewsapp.thedailynewscast.activities.activitiesfiles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.mohitdev.verticalviewpager.VerticalViewPager
import com.thenewsapp.activities.Network.NewsMainAllApiClient
import com.thenewsapp.activities.fragments.NoInternetFragment
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.SliderContents
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.fragments.NewsIndiaFragment
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.models.NewsMainAllResponseModel
import com.thenewsapp.thedailynewscast.activities.verticalviewpager.VerticalSwipeViewPager
import com.thenewsapp.thedailynewscast.activities.verticalviewpager.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var newsListAdapter: NewsListAdapter
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

        callNewsDataApi()

//        dbReference = FirebaseDatabase.getInstance().getReference("data")
//        dbReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (dataSnaps: DataSnapshot in snapshot.children) {
//                    title.add(dataSnaps.child("title").getValue(String::class.java)!!)
//                    content.add(dataSnaps.child("content").getValue(String::class.java)!!)
//                    imageUrl.add(dataSnaps.child("imageUrl").getValue(String::class.java)!!)
//                    readMoreUrl.add(dataSnaps.child("readMoreUrl").getValue(String::class.java)!!)
//                    url.add(dataSnaps.child("url").getValue(String::class.java)!!)
//                }
//
//                for (i in 0 until imageUrl.size) {
//                    sliderContents.add(SliderContents(imageUrl[i]))
//                }
//
//                mainVerticalViewPager.adapter =
//                    ViewPagerAdapter(
//                        this@MainActivity, sliderContents,
//                        title, content, imageUrl, readMoreUrl, url, mainVerticalViewPager
//                    )
//            }
//
//        })
    }


    private fun callNewsDataApi() {
        val newsApiClient = Network.getInstance().create(NewsMainAllApiClient::class.java)
        val call = newsApiClient.getNewsAllMainData()
        call.enqueue(object : Callback<NewsMainAllResponseModel> {
            override fun onFailure(call: Call<NewsMainAllResponseModel>, t: Throwable) {
                val noInternetFragment = NoInternetFragment()
                supportFragmentManager.beginTransaction().replace(R.id.llMainNoInternet, noInternetFragment, "NoInternetFragment")
                    .addToBackStack("noInternetFragment").commit()
            }

            override fun onResponse(
                call: Call<NewsMainAllResponseModel>,
                response: Response<NewsMainAllResponseModel>
            ) {
                response.body()?.let {

                    for (i in it.data?.indices!!) {
                       title.add(it.data[i]?.title.toString())
                        content.add(it.data[i]?.content.toString())
                        imageUrl.add(it.data[i]?.imageUrl.toString())
                       readMoreUrl.add(it.data[i]?.readMoreUrl.toString())
                        url.add(it.data[i]?.url.toString())
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
            }
        })
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