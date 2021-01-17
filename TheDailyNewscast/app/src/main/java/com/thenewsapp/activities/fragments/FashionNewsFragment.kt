package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.thedailynewscast.activities.Network.FashionApiClient
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.models.FashionResponseModel
import kotlinx.android.synthetic.main.fragment_fashion_news.*
import kotlinx.android.synthetic.main.fragment_travel_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FashionNewsFragment : Fragment(), RecyclerviewClickListener {

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
        return inflater.inflate(R.layout.fragment_fashion_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerFashion()
        flFashionProgressBar.visibility = View.VISIBLE
        callFashionApi()
    }

    private fun callFashionApi() {
        val fashionApiClient = Network.getInstance().create(FashionApiClient::class.java)
        val call = fashionApiClient.getFashionNewsData()
        call.enqueue(object : Callback<FashionResponseModel> {
            override fun onFailure(call: Call<FashionResponseModel>, t: Throwable) {
                flFashionProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<FashionResponseModel>,
                response: Response<FashionResponseModel>
            ) {
                response.body()?.let {
                    flFashionProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerFashion() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfFashion.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}