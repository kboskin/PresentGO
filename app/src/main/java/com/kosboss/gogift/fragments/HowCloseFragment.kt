package com.kosboss.gogift.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kosboss.gogift.R
import com.kosboss.gogift.seekbar.howclose.IndicatorSeekBar

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HowCloseFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HowCloseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HowCloseFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_how_close, container, false)
        val indicatorSeekBar: IndicatorSeekBar = view.findViewById(R.id.myOwnSeekBar)


        indicatorSeekBar.setOnSeekChangeListener(object : IndicatorSeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onSectionChanged(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int, textBelowTick: String?, fromUserTouch: Boolean) {
                when (thumbPosOnTick) {
                }

            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

            }

            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: IndicatorSeekBar?, progress: Int, progressFloat: Float, fromUserTouch: Boolean) {
            }
        })
        return view
    }

    companion object {
        fun newInstance(viewPager: ViewPager): HowCloseFragment {
            val fragment = HowCloseFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
