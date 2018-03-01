package com.kosboss.gogift

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.kosboss.gogift.fragments.*

/**
 * Created by hp on 025 25.02.2018.
 */
class PagesAdapter(fm: FragmentManager?, private val viewPager: ViewPager) : FragmentPagerAdapter(fm) {
    private val NUM_ITEMS = 6


    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return (WhomToGiftFragment.newInstance(viewPager))
        } else if (position == 1) {
            return GenderFragment.newInstance(viewPager)
        } else if (position == 2) {
            return AgeFragment.newInstance(viewPager)
        } else if (position == 3) {
            return WhatBudgetFragment.newInstance(viewPager)
        } else if (position == 4) {
            return OccasionFragment.newInstance(viewPager)
        } else if (position == 5) {
            return HowCloseFragment.newInstance(viewPager)
        } else {
            return WhatBudgetFragment.newInstance(viewPager)
        }
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

}