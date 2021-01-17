package com.thenewsapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.activities.Network.InternationalApiClient
import com.thenewsapp.activities.Network.StartupApiClient
import com.thenewsapp.activities.models.InternationalResponseModel
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import kotlinx.android.synthetic.main.fragment_international_news.*
import kotlinx.android.synthetic.main.fragment_startup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InternationalNewsFragment : Fragment(), RecyclerviewClickListener {

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
        return inflater.inflate(R.layout.fragment_international_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerInternational()
        flInternationalProgressBar.visibility = View.VISIBLE
        callInternationalApi()
    }

    private fun callInternationalApi() {
        val internationalApiClient = Network.getInstance().create(InternationalApiClient::class.java)
        val call = internationalApiClient.getInternationalNewsData()
        call.enqueue(object : Callback<InternationalResponseModel> {
            override fun onFailure(call: Call<InternationalResponseModel>, t: Throwable) {
                flInternationalProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<InternationalResponseModel>,
                response: Response<InternationalResponseModel>
            ) {
                response.body()?.let {
                    flInternationalProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerInternational() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfInternational.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}