package com.blacksmithdreamapps.presentgo.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.fragments.*
import fragments.HowCloseFragment


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class PagesAdapter(fm: FragmentManager?, private val viewPager: ViewPager) : FragmentPagerAdapter(fm) {
    private val NUM_ITEMS = 8


    var constants = Constants()
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WhomToGiftFragment.newInstance(viewPager)
            1 -> GenderFragment.newInstance(viewPager)
            2 -> AgeFragment.newInstance(viewPager)
            3 -> WhatBudgetFragment.newInstance(viewPager)
            4 -> OccasionFragment.newInstance(viewPager)
            5 -> HowCloseFragment.newInstance(viewPager)
            6 -> AfraidToForgetFragment.newInstance(viewPager)
            7 -> AfraidToForgetInputFragment.newInstance(viewPager)
            else -> WhatBudgetFragment.newInstance(viewPager)
        }
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

}