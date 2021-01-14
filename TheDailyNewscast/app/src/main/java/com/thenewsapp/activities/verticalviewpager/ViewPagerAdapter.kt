package com.thenewsapp.thedailynewscast.activities.verticalviewpager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.mohitdev.verticalviewpager.VerticalViewPager
import com.thenewsapp.thedailynewscast.activities.R
import com.thenewsapp.thedailynewscast.activities.SliderContents
import com.thenewsapp.thedailynewscast.activities.activitiesfiles.CategoryActivity
import com.thenewsapp.thedailynewscast.activities.activitiesfiles.NewsUrlDetailsActivity

class ViewPagerAdapter(
    private val context: Context,
    private val sliderContents: List<SliderContents>,
    private val title: ArrayList<String>,
    val content: ArrayList<String>,
    val imageUrl: ArrayList<String>,
    val readMoreUrl: ArrayList<String>,
    val url: ArrayList<String>,
    val date: ArrayList<String>,
    val time : ArrayList<String>,
    val mainVerticalViewPager: VerticalViewPager
) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private var newsPosition: Int? = null
    private var x1: Float? = null
    private var x2: Float? = null


    init {
        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater?.inflate(R.layout.news_content_layout, container, false)
        val imageView = itemView?.findViewById<ImageView>(R.id.ivNewsImg)
        if (imageView != null) {
            Glide.with(context).load(sliderContents[position].image).centerCrop().into(imageView)
        }

        val moreImgView = itemView?.findViewById<ImageView>(R.id.ivUrl)
        if (moreImgView != null) {
            Glide.with(context).load(sliderContents[position].image)
                .override(12, 12).centerCrop().into(moreImgView)

        }

        val newsTitle = itemView?.findViewById<TextView>(R.id.tvNewsHeadlines)
        newsTitle?.text = title[position]

        val newsContent = itemView?.findViewById<TextView>(R.id.tvNewsDesc)
        newsContent?.text = content[position]

        val newsHeading = itemView?.findViewById<TextView>(R.id.tvHeading)
        newsHeading?.text = title[position]

        val date = itemView?.findViewById<TextView>(R.id.tvDate)


        val time = itemView?.findViewById<TextView>(R.id.tvTime)


        mainVerticalViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                newsPosition = position
            }

        })
        mainVerticalViewPager.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        x1 = event.x
                    }

                    MotionEvent.ACTION_UP -> {
                        x2 = event.x
                        val deltaX: Float = x1!! - x2!!
                        val deltaY : Float = x2!! - x1!!

                        if (deltaX > 300) {
                            val urlIntent = Intent(context, NewsUrlDetailsActivity::class.java)
                            if (position == 1) {
                                urlIntent.putExtra("newsUrl", readMoreUrl[0])
                                context.startActivity(urlIntent)
                            } else {
                                urlIntent.putExtra("newsUrl", readMoreUrl[newsPosition!!])
                                context.startActivity(urlIntent)
                            }
                        } else  if(deltaY > 300){
                            val categoryIntent = Intent(context, CategoryActivity::class.java)
                            context.startActivity(categoryIntent)
                        }
                    }
                }
                return false
            }

        })

        container.addView(itemView)
        return itemView!!

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    override fun getCount(): Int {
        return sliderContents.size
    }

}