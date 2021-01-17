package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.MiscellaneousApiClient
import com.thenewsapp.activities.Network.StartupApiClient
import com.thenewsapp.activities.models.MiscellaneousResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_miscellanous_news.*
import kotlinx.android.synthetic.main.fragment_startup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MiscellanousNewsFragment : Fragment(), RecyclerviewClickListener {

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
        return inflater.inflate(R.layout.fragment_miscellanous_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerMiscellaneous()
        flMiscellanousProgressBar.visibility = View.VISIBLE
        callMiscellaneousApi()
    }

    private fun callMiscellaneousApi() {
        val miscellaneousApiClient = Network.getInstance().create(MiscellaneousApiClient::class.java)
        val call = miscellaneousApiClient.getMiscellaneousNewsData()
        call.enqueue(object : Callback<MiscellaneousResponseModel> {
            override fun onFailure(call: Call<MiscellaneousResponseModel>, t: Throwable) {
                flMiscellanousProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<MiscellaneousResponseModel>,
                response: Response<MiscellaneousResponseModel>
            ) {
                response.body()?.let {
                    flMiscellanousProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerMiscellaneous() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfMiscellanous.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}