package com.example.thedailynewscast.activities

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thedailynewscast.R
import com.example.thedailynewscast.UImodel.NewsUIModel
import com.example.thedailynewscast.adapter.NewsListAdapter
import com.example.thedailynewscast.models.DataItem
import com.example.thedailynewscast.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var newsListAdapter: NewsListAdapter
    private lateinit var newsViewModel: NewsViewModel
    private val dataItemList = emptyList<DataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setRecyclerData()
        observeLiveData()
        newsViewModel.callApi()
    }

    private fun setRecyclerData() {
        newsListAdapter = NewsListAdapter(dataItemList)
        val layoutManager = LinearLayoutManager(this)
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
                }
                is NewsUIModel.Failure -> {
                    Toast.makeText(
                        this,
                        "Error message ${it.error}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            AlertDialog.Builder(this)
                .setTitle("Do you want to Exit")
                .setNegativeButton("No", null)
                .setPositiveButton(
                    "Yes", DialogInterface.OnClickListener { dialog, which ->
                        val run =
                            Runnable { startActivity(Intent(this, ExitActivity::class.java)) }
                        val mHandler = Handler()
                        mHandler.postAtTime(run, 15000)
                        finish()
                        setResult(Activity.RESULT_OK, Intent().putExtra("Exit", true))
                        finish()
                    }).create().show()
        } else {
            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show()
        }

        backPressedTime = System.currentTimeMillis()
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}