package com.thenewsapp.thedailynewscast.activities.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.thedailynewscast.activities.Network.Network
import com.thenewsapp.thedailynewscast.activities.Network.SportsApiClient
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.models.SportsResponseModel
import kotlinx.android.synthetic.main.fragment_sports_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsViewFragment : Fragment(), RecyclerviewClickListener {
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
        return inflater.inflate(R.layout.fragment_sports_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerSports()
        flSportsProgressBar.visibility = View.VISIBLE
        callSportsApi()
    }

    private fun callSportsApi() {
        val sportsApiClient = Network.getInstance().create(SportsApiClient::class.java)
        val call = sportsApiClient.getSportsData()
        call.enqueue(object : Callback<SportsResponseModel> {
            override fun onFailure(call: Call<SportsResponseModel>, t: Throwable) {
                flSportsProgressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<SportsResponseModel>,
                response: Response<SportsResponseModel>
            ) {
                response.body()?.let {
                    flSportsProgressBar.visibility = View.GONE
                    newsListAdapter.updateNewsList(it.data as List<DataItem>)
                }
            }

        })
    }

    private fun setRecyclerSports() {
        newsListAdapter = NewsListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfSports.apply {
            this.layoutManager = layoutManager
            this.adapter = newsListAdapter
        }
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {

    }
}