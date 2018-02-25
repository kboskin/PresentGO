package com.kosboss.gogift

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by hp on 025 25.02.2018.
 */
class PagesAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val NUM_ITEMS = 3


    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return (WhomToGiftFragment.newInstance())

        }
        else{
            return( WhomToGiftFragment.newInstance())
        }
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }
}