package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.AutomobileApiClient
import com.thenewsapp.activities.Network.StartupApiClient
import com.thenewsapp.activities.models.AutomobileResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_automobile_news.*
import kotlinx.android.synthetic.main.fragment_startup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutomobileNewsFragment : Fragment(), RecyclerviewClickListener {
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
        return inflater.inflate(R.layout.fragment_automobile_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerAutomobile()
        flAutomobileProgressBar.visibility = View.VISIBLE
        callAutomobileApi()
    }

    private fun callAutomobileApi() {
        val automobileApiClient = Network.getInstance().create(AutomobileApiClient::class.java)
        val call = automobileApiClient.getAutomobileNewsData()
        call.enqueue(object : Callback<AutomobileResponseModel> {
            override fun onFailure(call: Call<AutomobileResponseModel>, t: Throwable) {
                flAutomobileProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<AutomobileResponseModel>,
                response: Response<AutomobileResponseModel>
            ) {
                response.body()?.let {
                    flAutomobileProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerAutomobile() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfAutomobile.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}