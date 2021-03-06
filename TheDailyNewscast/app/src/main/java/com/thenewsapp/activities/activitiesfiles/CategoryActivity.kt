package com.thenewsapp.thedailynewscast.activities.activitiesfiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.r0adkll.slidr.model.SlidrInterface
import com.thenewsapp.activities.activitiesfiles.SettingsActivity
import com.thenewsapp.activities.fragments.*
import com.thenewsapp.activities.models.AutomobileResponseModel
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.fragments.NewsEntertainmentFragment
import com.thenewsapp.thedailynewscast.activities.fragments.NewsIndiaFragment
import com.thenewsapp.thedailynewscast.activities.fragments.PoliticsViewFragment
import com.thenewsapp.thedailynewscast.activities.fragments.SportsViewFragment
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), View.OnClickListener {
    private var backPressedTime: Long = 0
    private var slider: SlidrInterface? = null

    private var x1: Float? = null
    private var y1: Float? = null
    private var x2: Float? = null
    private var y2: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        settingsBtn.setOnClickListener {
            openSettingsActivity()
        }

        nextBtn.setOnClickListener {
            openMainActivity()
        }

        rlSearchBar.setOnClickListener {
            openSearchActivity()
        }

        rlSearchBar.visibility = View.VISIBLE
        scrollView.visibility = View.VISIBLE
        cvPolitics.setOnClickListener(this)
        cvSports.setOnClickListener(this)
        cvEntertainment.setOnClickListener(this)
        cvIndia.setOnClickListener(this)
        cvBusiness.setOnClickListener(this)
        cvTechnology.setOnClickListener(this)
        cvTravel.setOnClickListener(this)
        cvFashion.setOnClickListener(this)
        cvStartUps.setOnClickListener(this)
        cvHatke.setOnClickListener(this)
        cvAutomobile.setOnClickListener(this)
        cvHealthFitness.setOnClickListener(this)
        cvEducation.setOnClickListener(this)
        cvScience.setOnClickListener(this)
        cvInternational.setOnClickListener(this)
        cvMiscellanous.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        rlSearchBar.visibility = View.VISIBLE
        scrollView.visibility = View.VISIBLE

        when (v?.id) {
            R.id.cvPolitics -> {
                openPoliticsFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvSports -> {
                openSportsFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvEntertainment -> {
                openEntertainmentFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvIndia -> {
                openIndiaFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvBusiness -> {
                openBusinessFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvTechnology -> {
                openTechnologyFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvTravel -> {
                openTravelFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvFashion -> {
                openFashionFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvStartUps -> {
                openStartupFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvHatke -> {
                openHatkeFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvAutomobile -> {
                openAutomobileFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvHealthFitness -> {
                openHealthFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvEducation -> {
                openEducationFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvScience -> {
                openScienceFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvInternational -> {
                openInternationalFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }

            R.id.cvMiscellanous -> {
                openMiscellanousFragment()
                rlSearchBar.visibility = View.GONE
                scrollView.visibility = View.GONE
            }
        }
    }

    private fun openMiscellanousFragment() {
        val miscellanousNewsFragment = MiscellanousNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, miscellanousNewsFragment, "MiscellanousNewsFragment")
            .addToBackStack("miscellanousNewsFragment").commit()
    }

    private fun openInternationalFragment() {
        val internationalNewsFragment = InternationalNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, internationalNewsFragment, "InternationalNewsFragment")
            .addToBackStack("internationalNewsFragment").commit()
    }

    private fun openScienceFragment() {
        val scienceNewsFragment = ScienceNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, scienceNewsFragment, "ScienceNewsFragment")
            .addToBackStack("scienceNewsFragment").commit()
    }

    private fun openEducationFragment() {
        val educationNewsFragment = EducationNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, educationNewsFragment, "EducationNewsFragment")
            .addToBackStack("educationNewsFragment").commit()
    }

    private fun openHealthFragment() {
       val healthFitnessFragment = HealthFitnessFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, healthFitnessFragment, "HealthFitnessFragment")
            .addToBackStack("healthFitnessFragment").commit()
    }

    private fun openAutomobileFragment() {
        val automobileNewsFragment = AutomobileNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, automobileNewsFragment, "AutomobileNewsFragment")
            .addToBackStack("automobileNewsFragment").commit()
    }

    private fun openHatkeFragment() {
        val hatkeNewsFragment = HatkeNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, hatkeNewsFragment, "HatkeNewsFragment")
            .addToBackStack("hatkeNewsFragment").commit()
    }

    private fun openStartupFragment() {
        val startupFragment = StartupFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, startupFragment, "StartupFragment")
            .addToBackStack("startupFragment").commit()
    }

    private fun openFashionFragment() {
        val fashionNewsFragment = FashionNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, fashionNewsFragment, "FashionNewsFragment")
            .addToBackStack("fashionNewsFragment").commit()
    }

    private fun openTravelFragment() {
        val travelNewsFragment = TravelNewsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, travelNewsFragment, "TravelNewsFragment")
            .addToBackStack("travelNewsFragment").commit()
    }

    private fun openTechnologyFragment() {
        val technologyFragment = NewsTechnologyFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, technologyFragment, "TechnologyFragment")
            .addToBackStack("technologyFragment").commit()
    }

    private fun openIndiaFragment() {
        val newsIndiaFragment = NewsIndiaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, newsIndiaFragment, "NewsIndiaFragment")
            .addToBackStack("newsIndiaFragment").commit()
    }

    private fun openEntertainmentFragment() {
        val entertainmentFragment = NewsEntertainmentFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, entertainmentFragment, "NewsEntertainmentFragment")
            .addToBackStack("newsEntertainmentFragment").commit()
    }

    private fun openSportsFragment() {
        val sportsViewFragment = SportsViewFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, sportsViewFragment, "SportsViewFragment")
            .addToBackStack("sportsViewFragment").commit()
    }

    private fun openBusinessFragment() {
        val businessFragment = NewsBusinessFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, businessFragment, "NewsBusinessFragment")
            .addToBackStack("newsBusinessFragment").commit()
    }

    private fun openPoliticsFragment() {
        val politicsViewFragment = PoliticsViewFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.llCategoryContainer, politicsViewFragment, "PoliticsViewFragment")
            .addToBackStack("politicsViewFragment").commit()
    }

    private fun openSettingsActivity() {
        val openSettingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(openSettingsIntent)
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

    override fun onBackPressed() {
        super.onBackPressed()
        rlSearchBar.visibility = View.VISIBLE
        scrollView.visibility = View.VISIBLE
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