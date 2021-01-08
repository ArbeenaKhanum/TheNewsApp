package com.thenewsapp.thedailynewscast.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.thenewsapp.thedailynewscast.fragments.NewsDetailsFragment

class FragmentsAdapter(fm : FragmentManager , behaviour : Int) : FragmentPagerAdapter(fm, behaviour) {
    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return NewsDetailsFragment()
            }

            1 -> {
                return NewsDetailsFragment()
            }

            3 -> {
                return NewsDetailsFragment()
            }

            4 -> {
                return NewsDetailsFragment()
            }

            5 -> {
                return NewsDetailsFragment()
            }

            6 -> {
                return NewsDetailsFragment()
            }

            7 -> {
                return NewsDetailsFragment()
            }

            8 -> {
                return NewsDetailsFragment()
            }

            9 -> {
                return NewsDetailsFragment()
            }
        }
        return NewsDetailsFragment()
    }

    override fun getCount(): Int {
        return 10;
    }
}