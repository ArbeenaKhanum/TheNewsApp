package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.HealthFitnessApiClient
import com.thenewsapp.activities.Network.StartupApiClient
import com.thenewsapp.activities.models.HealthResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_health_fitness.*
import kotlinx.android.synthetic.main.fragment_startup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HealthFitnessFragment : Fragment(), RecyclerviewClickListener {

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
        return inflater.inflate(R.layout.fragment_health_fitness, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerHealth()
        flHealthProgressBar.visibility = View.VISIBLE
        callHealthApi()
    }

    private fun callHealthApi() {
        val healthFitnessApiClient = Network.getInstance().create(HealthFitnessApiClient::class.java)
        val call = healthFitnessApiClient.getHealthFitnessNewsData()
        call.enqueue(object : Callback<HealthResponseModel> {
            override fun onFailure(call: Call<HealthResponseModel>, t: Throwable) {
                flHealthProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<HealthResponseModel>,
                response: Response<HealthResponseModel>
            ) {
                response.body()?.let {
                    flHealthProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerHealth() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfHealthFitness.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}