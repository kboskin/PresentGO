package com.kosboss.gogift

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kosboss.gogift.fragments.GenderFragment
import com.kosboss.gogift.fragments.WhatBudgetFragment
import com.kosboss.gogift.fragments.WhomToGiftFragment

/**
 * Created by hp on 025 25.02.2018.
 */
class PagesAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val NUM_ITEMS = 3


    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return (WhomToGiftFragment.newInstance())

        }
        else if (position == 1)
        {
            return GenderFragment.newInstance()
        }
        else if (position == 2){
            return WhatBudgetFragment.newInstance()
        }
        else{
            return WhatBudgetFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }
}