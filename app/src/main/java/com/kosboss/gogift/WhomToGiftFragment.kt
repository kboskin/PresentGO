package com.kosboss.gogift

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WhomToGiftFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WhomToGiftFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WhomToGiftFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater!!.inflate(R.layout.fragment_whom_to_gift, container, false)


        return view
    }


    companion object {

        fun newInstance(): WhomToGiftFragment {
            val fragment = WhomToGiftFragment()
            return fragment
        }
    }
}