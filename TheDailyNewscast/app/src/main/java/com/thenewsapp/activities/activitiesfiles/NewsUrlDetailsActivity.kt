package com.thenewsapp.thedailynewscast.activities.activitiesfiles

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.thenewsapp.thedailynewscast.activities.R
import kotlinx.android.synthetic.main.activity_news_url_details.*

class NewsUrlDetailsActivity : AppCompatActivity() {
    private var newsUrlLink: String? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_url_details)

        val newsUrlIntent: Intent = intent
        newsUrlLink = newsUrlIntent.getStringExtra("newsUrl")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    webView.loadUrl(url)
                }
                return false
            }
        }

        webView.settings.javaScriptEnabled =  true
        webView.loadUrl(newsUrlLink!!)
    }
}