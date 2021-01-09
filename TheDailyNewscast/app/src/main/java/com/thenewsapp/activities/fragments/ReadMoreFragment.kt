package com.thenewsapp.thedailynewscast.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.thenewsapp.thedailynewscast.activities.R
import kotlinx.android.synthetic.main.fragment_read_more.*

class ReadMoreFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getReadMoreBundle()
    }

    private fun getReadMoreBundle() {
        val rmImg = arguments!!.getString("readMoreImg")
        Glide.with(ivReadMoreNewsImg).load(rmImg).into(ivReadMoreNewsImg)
        val rmHeadline = arguments!!.getString("readMoreHeadline")
        tvNewsReadMoreHeadlines.text = rmHeadline
        val rmNewsDesc = arguments!!.getString("readMoreDesc")
        tvReadMoreNewsDesc.text = rmNewsDesc
        val rmNewsTime = arguments!!.getString("readMoreTime")
        tvReadMoreTime.text = rmNewsTime
        val rmNewsDate = arguments!!.getString("readMoreDate")
        tvReadMoreDate.text = rmNewsDate
        val rmNewsAuthor = arguments!!.getString("readMoreAuthor")
        tvReadMoreAuthor.text = rmNewsAuthor
    }
}