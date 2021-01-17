package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.EducationApiClient
import com.thenewsapp.activities.Network.ScienceApiClient
import com.thenewsapp.activities.models.ScienceResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_education_news.*
import kotlinx.android.synthetic.main.fragment_science_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScienceNewsFragment : Fragment(), RecyclerviewClickListener {

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
        return inflater.inflate(R.layout.fragment_science_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerScience()
        flScienceProgressBar.visibility = View.VISIBLE
        callScienceApi()
    }

    private fun callScienceApi() {
        val scienceApiClient = Network.getInstance().create(ScienceApiClient::class.java)
        val call = scienceApiClient.getScienceNewsData()
        call.enqueue(object : Callback<ScienceResponseModel> {
            override fun onFailure(call: Call<ScienceResponseModel>, t: Throwable) {
                flScienceProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<ScienceResponseModel>,
                response: Response<ScienceResponseModel>
            ) {
                response.body()?.let {
                    flScienceProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerScience() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfScience.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}