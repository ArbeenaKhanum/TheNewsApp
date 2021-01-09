package com.thenewsapp.thedailynewscast.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.thedailynewscast.activities.Network.EntertainmentApiClient
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.models.EntertainmentResponseModel
import kotlinx.android.synthetic.main.fragment_news_entertainment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsEntertainmentFragment : Fragment(), RecyclerviewClickListener {
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
        return inflater.inflate(R.layout.fragment_news_entertainment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerEntertainment()
        flEntertainmentProgressBar.visibility = View.VISIBLE
        callEntertainmentApi()
    }

    private fun callEntertainmentApi() {
        val entertainmentApiClient = Network.getInstance().create(EntertainmentApiClient::class.java)
        val call = entertainmentApiClient.getEntertainmentData()
        call.enqueue(object : Callback<EntertainmentResponseModel>{
            override fun onFailure(call: Call<EntertainmentResponseModel>, t: Throwable) {
                flEntertainmentProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<EntertainmentResponseModel>,
                response: Response<EntertainmentResponseModel>
            ) {
                response.body()?.let {
                    flEntertainmentProgressBar.visibility = View.VISIBLE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })

    }

    private fun setRecyclerEntertainment()  {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfEntertainment.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}