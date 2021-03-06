package com.thenewsapp.activities.activitiesfiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.thenewsapp.thedailynewscast.activities.R
import kotlinx.android.synthetic.main.activity_logged_in_setting.*

class LoggedInSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in_setting)

        ibGoogleSignOut.setOnClickListener {
            logOutFromGoogleAcc()
        }

        llLoginShare.setOnClickListener {
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

    private fun logOutFromGoogleAcc() {
        Firebase.auth.signOut()

        val logOutIntent = Intent(this, SettingsActivity::class.java)
        startActivity(logOutIntent)
    }
}