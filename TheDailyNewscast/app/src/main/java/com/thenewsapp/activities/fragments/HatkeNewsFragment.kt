package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.HatkeApiClient
import com.thenewsapp.activities.Network.StartupApiClient
import com.thenewsapp.activities.models.HatkeResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_hatke_news.*
import kotlinx.android.synthetic.main.fragment_startup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HatkeNewsFragment : Fragment(), RecyclerviewClickListener {

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
        return inflater.inflate(R.layout.fragment_hatke_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerHatke()
        flHatkeProgressBar.visibility = View.VISIBLE
        callHatkeApi()
    }

    private fun callHatkeApi() {
        val hatkeApiClient = Network.getInstance().create(HatkeApiClient::class.java)
        val call = hatkeApiClient.getHatkeNewsData()
        call.enqueue(object : Callback<HatkeResponseModel> {
            override fun onFailure(call: Call<HatkeResponseModel>, t: Throwable) {
                flHatkeProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<HatkeResponseModel>,
                response: Response<HatkeResponseModel>
            ) {
                response.body()?.let {
                    flHatkeProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerHatke() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfHatke.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}