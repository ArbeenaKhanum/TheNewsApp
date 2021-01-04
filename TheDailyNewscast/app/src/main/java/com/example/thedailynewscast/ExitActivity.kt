package com.example.thedailynewscast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class ExitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)

        val runnable = Runnable {
            finish()
        }
        val handler = Handler()
        handler.postDelayed(runnable, 2000)
    }
}