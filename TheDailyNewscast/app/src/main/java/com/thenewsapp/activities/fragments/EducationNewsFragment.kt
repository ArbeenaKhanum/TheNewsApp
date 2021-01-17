package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.EducationApiClient
import com.thenewsapp.activities.Network.HealthFitnessApiClient
import com.thenewsapp.activities.models.EducationResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_education_news.*
import kotlinx.android.synthetic.main.fragment_health_fitness.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationNewsFragment : Fragment(), RecyclerviewClickListener {

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
        return inflater.inflate(R.layout.fragment_education_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerEducation()
        flEducationProgressBar.visibility = View.VISIBLE
        callEducationApi()
    }

    private fun callEducationApi() {
        val educationApiClient = Network.getInstance().create(EducationApiClient::class.java)
        val call = educationApiClient.getEducationNewsData()
        call.enqueue(object : Callback<EducationResponseModel> {
            override fun onFailure(call: Call<EducationResponseModel>, t: Throwable) {
                flEducationProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<EducationResponseModel>,
                response: Response<EducationResponseModel>
            ) {
                response.body()?.let {
                    flEducationProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerEducation() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfEducation.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}