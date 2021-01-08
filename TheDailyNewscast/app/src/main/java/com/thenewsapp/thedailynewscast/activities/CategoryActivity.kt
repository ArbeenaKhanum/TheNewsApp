package com.thenewsapp.thedailynewscast.activities

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.thenewsapp.thedailynewscast.R
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrInterface
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private var slider: SlidrInterface? = null

    private var x1: Float? = null
    private var y1: Float? = null
    private var x2: Float? = null
    private var y2: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        nextBtn.setOnClickListener {
            openMainActivity()
        }

        rlSearchBar.setOnClickListener {
            openSearchActivity();
        }

    }

    private fun openSearchActivity() {
        val openSearchIntent = Intent(this, SearchActivity::class.java)
        startActivity(openSearchIntent)
    }

    private fun openMainActivity() {
        val openMainIntent = Intent(this, MainActivity::class.java)
        startActivity(openMainIntent)
    }

    fun lockSlide(view: View) {
        slider?.lock()
    }

    fun unLockSlide(view: View) {
        slider?.unlock()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                y1 = event.y
            }

            MotionEvent.ACTION_UP -> {
                x2 = event.x
                y2 = event.y

                if (x1!! > x2!!) {
                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)
                }
            }
        }
        return false
    }

//    override fun onBackPressed() {
//
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//
//            AlertDialog.Builder(this)
//                .setTitle("Do you want to Exit")
//                .setNegativeButton("No", null)
//                .setPositiveButton(
//                    "Yes", DialogInterface.OnClickListener { dialog, which ->
//                        val run =
//                            Runnable { startActivity(Intent(this, ExitActivity::class.java)) }
//                        val mHandler = Handler()
//                        mHandler.postAtTime(run, 15000)
//                        finish()
//                        setResult(Activity.RESULT_OK, Intent().putExtra("Exit", true))
//                        finish()
//                    }).create().show()
//        } else {
//            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show()
//        }
//
//        backPressedTime = System.currentTimeMillis()
//    }
}