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
 * [GenderFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GenderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenderFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_gender, container, false)
    }


    companion object {
        fun newInstance(viewPager: ViewPager): GenderFragment {
            val fragment = GenderFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
