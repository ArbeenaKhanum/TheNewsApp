package com.example.thedailynewscast.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.thedailynewscast.R

class ExitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)

        val runnable = Runnable {
            finish()
        }
        val handler = Handler()
        handler.postDelayed(runnable, 2700)
    }
}