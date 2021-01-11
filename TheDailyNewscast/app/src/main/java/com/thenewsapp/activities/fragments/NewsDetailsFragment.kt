package com.thenewsapp.thedailynewscast.activities.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.UImodel.NewsSearchUIModel
import com.thenewsapp.thedailynewscast.activities.adapter.NewsSearchListAdapter
import com.thenewsapp.thedailynewscast.activities.listener.ReadMoreNewsListener
import com.thenewsapp.thedailynewscast.activities.listener.RecyclerviewClickListener
import com.thenewsapp.thedailynewscast.activities.models.DataItem
import com.thenewsapp.thedailynewscast.activities.viewmodel.NewsSearchViewModel
import kotlinx.android.synthetic.main.fragment_news_details.*


class NewsDetailsFragment : Fragment(), RecyclerviewClickListener {
    private lateinit var newsSearchListAdapter: NewsSearchListAdapter
    private lateinit var newsSearchViewModel: NewsSearchViewModel
    private val dataItemList = emptyList<DataItem>()

//    private var urlFragmentListener: UrlFragmentListener? = null
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

//        searchBackArrow.setOnClickListener {
//            val openDiscoverIntent = Intent(this, CategoryActivity::class.java)
//            startActivity(openDiscoverIntent)
//        }

        tvSearchForNews.visibility = View.VISIBLE

        tvSearchForNews.setOnClickListener {
            tvSearchForNews.visibility = View.GONE
        }

        newsSearchViewModel = ViewModelProvider(requireActivity()).get(NewsSearchViewModel::class.java)
        setRecyclerSearchData()
        observeSearchNewsData()
        searchNewsList()
    }

    private fun searchNewsList() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                newsSearchViewModel.callSearchApi(query.toString())
                tvSearchForNews.visibility = View.GONE
                flProgressBar.visibility = View.VISIBLE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setRecyclerSearchData() {
        newsSearchListAdapter = NewsSearchListAdapter(dataItemList, this)
        val layoutManager = LinearLayoutManager( context)
        rlListOfSearch.apply {
            this.layoutManager = layoutManager
            this.adapter = newsSearchListAdapter
        }
    }

    private fun observeSearchNewsData() {
        newsSearchViewModel.liveDataSearchNews.observe(this, Observer {
            when(it) {
                is NewsSearchUIModel.Success -> {
                    newsSearchListAdapter.updateNewsSearchList(it.newsResponseModel.data as List<DataItem>)
                    tvSearchForNews.visibility = View.GONE
                    flProgressBar.visibility = View.GONE
                }

                is NewsSearchUIModel.Failure -> {
                    Toast.makeText(
                        context,
                        "Error message ${it.error}",
                        Toast.LENGTH_SHORT
                    )
                    flProgressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        urlFragmentListener = context as UrlFragmentListener
        readMoreNewsListener = context as ReadMoreNewsListener
    }

//    override fun onUrlClicked(position: Int, dataItem: DataItem) {
//        urlFragmentListener?.onFragmentDataPassed(dataItem)
//    }

    override fun onReadMoreClicked(position: Int, dataItem: DataItem) {
        readMoreNewsListener?.onFragmentReadMorePassed(dataItem)
    }
}