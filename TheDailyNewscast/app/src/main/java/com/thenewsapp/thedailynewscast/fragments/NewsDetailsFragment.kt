package com.thenewsapp.thedailynewscast.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.thedailynewscast.R
import com.thenewsapp.thedailynewscast.UImodel.NewsUIModel
import com.thenewsapp.thedailynewscast.adapter.NewsListAdapter
import com.thenewsapp.thedailynewscast.listener.ReadMoreNewsListener
import com.thenewsapp.thedailynewscast.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.listener.UrlFragmentListener
import com.thenewsapp.thedailynewscast.models.DataItem
import com.thenewsapp.thedailynewscast.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_details.*


class NewsDetailsFragment : Fragment(), RecyclerviewClickListener {
    private lateinit var newsListAdapter: NewsListAdapter
    private lateinit var newsViewModel: NewsViewModel
    private val dataItemList = emptyList<DataItem>()

    private var urlFragmentListener: UrlFragmentListener? = null
    private var readMoreNewsListener : ReadMoreNewsListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setRecyclerData()
        observeLiveData()
        flProgressBar.visibility = View.VISIBLE
        newsViewModel.callApi()
    }

    private fun setRecyclerData() {
        newsListAdapter = NewsListAdapter(dataItemList, this)

        rlListOfNews.overScrollMode = View.OVER_SCROLL_NEVER
        val layoutManager = LinearLayoutManager(context)
        rlListOfNews.apply {
            this.layoutManager = layoutManager
            adapter = newsListAdapter
        }
    }

    private fun observeLiveData() {
        newsViewModel.liveDataForNews.observe(this, Observer {
            when (it) {

                is NewsUIModel.Success -> {
                    newsListAdapter.updateNewsList(it.dataItemList)
                    flProgressBar.visibility = View.GONE
                }
                is NewsUIModel.Failure -> {
                    Toast.makeText(
                        context,
                        "Error message ${it.error}",
                        Toast.LENGTH_SHORT
                    ).show()
                    flProgressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        urlFragmentListener = context as UrlFragmentListener
        readMoreNewsListener = context as ReadMoreNewsListener
    }

    override fun onUrlClicked(position: Int, dataItem: DataItem) {
        urlFragmentListener?.onFragmentDataPassed(dataItem)
    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {
        readMoreNewsListener?.onFragmentReadMorePassed(dataItem)
    }
}