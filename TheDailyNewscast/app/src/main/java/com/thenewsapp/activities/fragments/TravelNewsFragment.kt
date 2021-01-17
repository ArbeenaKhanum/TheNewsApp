package com.thenewsapp.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.Network.TravelApiClient
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.models.TravelResponseModel
import kotlinx.android.synthetic.main.fragment_travel_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TravelNewsFragment : Fragment(), RecyclerviewClickListener {
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
        return inflater.inflate(R.layout.fragment_travel_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerTravel()
        flTravelProgressBar.visibility = View.VISIBLE
        callTravelApi()
    }

    private fun callTravelApi() {
        val travelApiClient = Network.getInstance().create(TravelApiClient::class.java)
        val call = travelApiClient.getTravelNewsData()
        call.enqueue(object : Callback<TravelResponseModel> {
            override fun onFailure(call: Call<TravelResponseModel>, t: Throwable) {
                flTravelProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<TravelResponseModel>,
                response: Response<TravelResponseModel>
            ) {
                response.body()?.let {
                    flTravelProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerTravel() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfTravel.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}