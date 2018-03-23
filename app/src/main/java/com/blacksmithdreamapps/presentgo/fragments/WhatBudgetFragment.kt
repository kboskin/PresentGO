package com.blacksmithdreamapps.presentgo.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.R
import com.blacksmithdreamapps.presentgo.events.PagerPasserEvent
import com.blacksmithdreamapps.presentgo.seekbar.budget.IndicatorSeekBar
import kotlinx.android.synthetic.main.fragment_what_budget.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class WhatBudgetFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        editor.putString(constants.BUDGET, budget)
        editor.apply()
        viewPager.currentItem = viewPager.currentItem + 1
    }

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var budget : String;
    lateinit var constants: Constants
    lateinit var viewPager: ViewPager

    // handling passing here viewPager via eventbus
    var bus = EventBus.getDefault()
    @Subscribe
    public fun setUpViewPager(event : PagerPasserEvent)
    {
        viewPager = event.viewPager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bus.register(this)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_what_budget, container, false)
        val indicatorSeekBar: IndicatorSeekBar = view.findViewById(R.id.myOwnSeekBar)

        constants = Constants()


        // default value
        budget = constants.BUDGET_MIDDLE

        preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
        editor = preferences.edit();

        view.button_done_budget.setOnClickListener(this)

        indicatorSeekBar.setOnSeekChangeListener(object : IndicatorSeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onSectionChanged(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int, textBelowTick: String?, fromUserTouch: Boolean) {

            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

            }

            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: IndicatorSeekBar?, progress: Int, progressFloat: Float, fromUserTouch: Boolean) {
                val prices: Array<out String>? = resources.getStringArray(R.array.prices)

                when (progress) {
                    15 -> budget = constants.BUDGET_MINIMAL
                    86 -> budget = constants.BUDGET_LOW
                    158 -> budget = constants.BUDGET_MIDDLE
                    229 -> budget = constants.BUDGET_MIDDLE_PLUS
                    300 -> budget = constants.BUDGET_HIGH
                }
            }
        })
        return view
    }

    companion object {
        fun newInstance(viewPager: ViewPager, constants: Constants): WhatBudgetFragment {
            val fragment = WhatBudgetFragment()
            fragment.constants = constants
            fragment.viewPager = viewPager
            return fragment
        }
    }
}// Required empty public constructor
