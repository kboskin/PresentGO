package com.kosboss.gogift.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.kosboss.gogift.R
import kotlinx.android.synthetic.main.fragment_afraid_to_forget.view.*
import org.joda.time.DateTime

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AfraidToForgetFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AfraidToForgetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AfraidToForgetFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_afraid_to_forget, container, false)
        // formatter for printing months instead of days
        val formatter = NumberPicker.Formatter {
            val months = context.resources.getStringArray(R.array.months)
            when (it) {
                1 -> return@Formatter months[0]
                2 -> return@Formatter months[1]
                3 -> return@Formatter months[2]
                4 -> return@Formatter months[3]
                5 -> return@Formatter months[4]
                6 -> return@Formatter months[5]
                7 -> return@Formatter months[6]
                8 -> return@Formatter months[7]
                9 -> return@Formatter months[8]
                10 -> return@Formatter months[9]
                11 -> return@Formatter months[10]
                12 -> return@Formatter months[11]
                else -> return@Formatter null
            }
        }
        view.month_picker.setFormatter(formatter)
        // default for months
        view.month_picker.value = DateTime.now().monthOfYear
        // default for days
        view.day_picker.value = DateTime.now().dayOfMonth


        return view
    }


    companion object {
        fun newInstance(viewPager: ViewPager): AfraidToForgetFragment {
            val fragment = AfraidToForgetFragment()
            val args = Bundle()
            return fragment
        }
    }
}// Required empty public constructor
