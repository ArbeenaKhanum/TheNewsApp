package com.thenewsapp.thedailynewscast.activities.activitiesfiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.thenewsapp.thedailynewscast.activities.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val runnable = Runnable {
            val splashIntent = Intent(this, MainActivity::class.java)
            startActivity(splashIntent)
            finish()
        }
        val handler = Handler()
        handler.postDelayed(runnable, 3200)

        animationOnSplash()
    }

    private fun animationOnSplash() {
        val splashAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.transitions_splash
        )
        val lTRAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.left_to_right
        )
        val rTLAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.right_to_left
        )
        tvTheDaily.animation = lTRAnimation
        ivNewsLogo.animation = splashAnimation
        tvNewscast.animation = rTLAnimation
        newsIconOne.animation = splashAnimation
        newsIconTwo.animation = splashAnimation
    }
}