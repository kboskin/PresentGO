package com.kosboss.gogift.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kosboss.gogift.R
import com.kosboss.gogift.seekbar.IndicatorSeekBar


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WhatBudgetFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WhatBudgetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WhatBudgetFragment : Fragment() {

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_what_budget, container, false)
        val indicatorSeekBar: IndicatorSeekBar = view.findViewById(R.id.myOwnSeekBar)


        indicatorSeekBar.setOnSeekChangeListener(object : IndicatorSeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onSectionChanged(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int, textBelowTick: String?, fromUserTouch: Boolean) {
                val prices: Array<out String>? = resources.getStringArray(R.array.prices)
                when (thumbPosOnTick) {
                }

            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

            }

            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: IndicatorSeekBar?, progress: Int, progressFloat: Float, fromUserTouch: Boolean) {
                val prices: Array<out String>? = resources.getStringArray(R.array.prices)

                when (progress) {
/*
                    15 -> hintTextView.text = prices!![0]
                    86 -> hintTextView.text = prices!![1]
                    158 -> hintTextView.text = prices!![2]
                    229 -> hintTextView.text = prices!![3]
                    300 -> hintTextView.text = prices!![4] + prices[5]
*/
                }
            }
        })
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event

    companion object {
        fun newInstance(): WhatBudgetFragment {
            val fragment = WhatBudgetFragment()
            val args = Bundle()
            return fragment
        }
    }
}// Required empty public constructor
