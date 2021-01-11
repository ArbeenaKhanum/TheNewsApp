package com.thenewsapp.activities.activitiesfiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.activitiesfiles.CategoryActivity
import com.thenewsapp.thedailynewscast.activities.activitiesfiles.LoginActivity
import kotlinx.android.synthetic.main.activity_logged_in_setting.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.settingsBackArrow

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharePreferenceSignin.setOnClickListener {
            openLogInActivity()
        }

        settingsBackArrow.setOnClickListener {
            openCategoryActivity()
        }

        llSignOutShare.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Stay updated with the latest news with The Daily Newscast App. Get it at https://thedailynewscast.com/dl/"
            );
            startActivity(
                Intent.createChooser(
                    shareIntent,
                    getString(R.string.share_app)
                )
            )
        }
    }

    private fun openCategoryActivity() {
        val openCategory = Intent(this@SettingsActivity,CategoryActivity::class.java)
        startActivity(openCategory)
    }


    private fun openLogInActivity() {
        val openLoginIntent = Intent(this@SettingsActivity, LoginActivity::class.java)
        startActivity(openLoginIntent)
    }
}