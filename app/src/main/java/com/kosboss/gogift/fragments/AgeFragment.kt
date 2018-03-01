package com.kosboss.gogift.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kosboss.gogift.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AgeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AgeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_age, container, false)
    }


    companion object {
        // TODO: Rename and change types and number of parameters
        fun newInstance(viewPager: ViewPager): AgeFragment {
            val fragment = AgeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
