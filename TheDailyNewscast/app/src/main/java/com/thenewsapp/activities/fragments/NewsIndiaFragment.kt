package com.thenewsapp.thedailynewscast.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.thedailynewscast.activities.Network.IndiaListApiClient
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.models.IndiaResponseModel
import kotlinx.android.synthetic.main.fragment_news_india.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsIndiaFragment : Fragment(), RecyclerviewClickListener {
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
        return inflater.inflate(R.layout.fragment_news_india, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerIndiaList()
        flIndiaProgressBar.visibility = View.VISIBLE
        callIndiaListApi()
    }

    private fun callIndiaListApi() {
        val indiaListApiClient = Network.getInstance().create(IndiaListApiClient::class.java)
        val call = indiaListApiClient.getIndiaNewsData()
        call.enqueue(object : Callback<IndiaResponseModel>{
            override fun onFailure(call: Call<IndiaResponseModel>, t: Throwable) {
                flIndiaProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<IndiaResponseModel>,
                response: Response<IndiaResponseModel>
            ) {
                response.body()?.let {
                    flIndiaProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }
        })

    }

    private fun setRecyclerIndiaList()  {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfIndia.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}