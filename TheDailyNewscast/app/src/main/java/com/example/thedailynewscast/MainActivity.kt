package com.example.thedailynewscast

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var singleBack = false

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