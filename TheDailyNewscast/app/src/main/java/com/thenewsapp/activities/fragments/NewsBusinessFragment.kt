package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.BusinessApiClient
import com.thenewsapp.activities.models.BusinessResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_news_business.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsBusinessFragment : Fragment(), RecyclerviewClickListener {

    private lateinit var newsListAdapter: NewsListAdapter
    private val dataItemList = emptyList<DataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerBusiness()
        flBusinessProgressBar.visibility = View.VISIBLE
        callBusinessApi()
    }

    private fun callBusinessApi() {
        val businessApiClient = Network.getInstance().create(BusinessApiClient::class.java)
        val call = businessApiClient.getNewsBusinessData()
        call.enqueue(object : Callback<BusinessResponseModel> {
            override fun onFailure(call: Call<BusinessResponseModel>, t: Throwable) {
                flBusinessProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<BusinessResponseModel>,
                response: Response<BusinessResponseModel>
            ) {
                response.body()?.let {
                    flBusinessProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerBusiness() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfBusiness.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }

}