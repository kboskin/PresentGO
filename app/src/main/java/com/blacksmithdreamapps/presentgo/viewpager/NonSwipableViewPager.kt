package com.blacksmithdreamapps.presentgo.viewpager

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import com.blacksmithdreamapps.presentgo.Constants


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class NonSwipableViewPager : ViewPager {

    constructor(context: Context) : super(context) {
        setMyScroller()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setMyScroller()
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    //down one is added for smooth scrolling

    private fun setMyScroller() {
        try {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, MyScroller(context))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    inner class MyScroller(context: Context) : Scroller(context, DecelerateInterpolator()) {

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {

            val preferences = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
            val allEntries = preferences.getAll()
            for (entry in allEntries.entries) {
                Log.d("PrefsValuesPager", entry.key + ": " + entry.value.toString())
            }
            val skipAnim = preferences.getBoolean(Constants.PREFS_ANIMATION, false)
            Log.d("SomeTag", "skip_anim" + skipAnim.toString())
            if (skipAnim) {
                super.startScroll(startX, startY, dx, dy, 0 /*1 secs*/)
            } else {

                super.startScroll(startX, startY, dx, dy, 300 /*1 secs*/)

            }

        }
    }
}
