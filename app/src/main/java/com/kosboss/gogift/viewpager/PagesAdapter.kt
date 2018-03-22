package com.kosboss.gogift.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.kosboss.gogift.Constants
import com.kosboss.gogift.fragments.*

/**
 * Created by hp on 025 25.02.2018.
 */
class PagesAdapter(fm: FragmentManager?, private val viewPager: ViewPager) : FragmentPagerAdapter(fm) {
    private val NUM_ITEMS = 8


    var constants = Constants()
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WhomToGiftFragment.newInstance(viewPager, constants)
            1 -> GenderFragment.newInstance(viewPager, constants)
            2 -> AgeFragment.newInstance(viewPager, constants)
            3 -> WhatBudgetFragment.newInstance(viewPager, constants)
            4 -> OccasionFragment.newInstance(viewPager, constants)
            5 -> HowCloseFragment.newInstance(viewPager, constants)
            6 -> AfraidToForgetFragment.newInstance(viewPager, constants)
            7 -> AfraidToForgetInputFragment.newInstance(viewPager)
            else -> WhatBudgetFragment.newInstance(viewPager, constants)
        }
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

}