package com.thenewsapp.thedailynewscast.activities.verticalviewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs


class VerticalSwipeViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    init {
        setPageTransformer(true,
            VerticalViewPagerTransform()
        )
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun swapXY(event: MotionEvent): MotionEvent? {
        val newX = event.y
        val newY = event.x
        event.setLocation(newX, newY)
        return event
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }

    override fun canScrollVertically(direction: Int): Boolean {
        return super.canScrollHorizontally(direction)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercept = super.onInterceptTouchEvent(swapXY(ev))
        swapXY(ev)
        return intercept
    }


    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val toHandle = super.onTouchEvent(swapXY(ev))
        swapXY(ev)
        return toHandle
    }

    private class VerticalViewPagerTransform : PageTransformer {
        override fun transformPage(page: View, position: Float) {
            if (position < -1) {
                page.alpha = 0f
            } else if (position <= 0) {
                page.alpha = 1f
                page.translationX = page.width * -position
                page.translationY = page.height * position
                page.scaleX = 1f
                page.scaleY = 1f
            } else if (position <= 1) {
                page.alpha = 1 - position
                page.translationX = page.width * -position
                page.translationY = 0f
                val scaleFactor =
                    Min_Scale + (1 - Min_Scale) * (1 - abs(position))
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
            } else if (position > 1) {
                page.alpha = 0f
            }
        }

        companion object {
            private const val Min_Scale = 0.65f
        }
    }

}