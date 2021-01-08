package com.thenewsapp.thedailynewscast.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thenewsapp.thedailynewscast.MyWebViewClient
import com.thenewsapp.thedailynewscast.R
import kotlinx.android.synthetic.main.fragment_url_view.*


class UrlViewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_url_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
    }

    private fun getDataFromBundle() {
        val urlLink = arguments!!.getString("urlLinkText")
        tvUrlText.webViewClient = MyWebViewClient(this)
        if (urlLink != null) {
            tvUrlText.loadUrl(urlLink)
        }
    }
}